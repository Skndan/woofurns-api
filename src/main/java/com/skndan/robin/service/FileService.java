package com.skndan.robin.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.UUID;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

import com.skndan.robin.entity.FileEntity;
import com.skndan.robin.repo.FileRepo;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.MultivaluedMap;

@ApplicationScoped
public class FileService {

  @Inject
  FileRepo fileRepo; // Ensure your FileRepo is adapted for Quarkus as well

  @Transactional
  public FileEntity uploadFile(String staticPath, String fileName, InputStream fileInputStream) {

    FileEntity fileInfo = new FileEntity();

    try {
      // Generate new file name if incoming is empty
      // UUID uuid = UUID.randomUUID();
      // String extension = fileName.substring(fileName.lastIndexOf("."));
      // fileName = uuid.toString() + extension;

      // Extract folder name from the static path
      String folderName = Paths.get(staticPath).getFileName().toString();

      // Creating the directory if not present
      File uploadDir = new File(staticPath);
      if (!uploadDir.exists() && !uploadDir.mkdirs()) {
        throw new RuntimeException("Failed to create upload directory");
      }

      // Create a new file in the upload directory
      File newFile = new File(uploadDir, fileName);
      try (OutputStream os = new FileOutputStream(newFile)) {
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = fileInputStream.read(buffer)) != -1) {
          os.write(buffer, 0, bytesRead);
        }
      }

      String uuid = UUID.randomUUID().toString();

      // Construct the file URL
      String fileUrl = "/" + folderName + "/" + fileName;
      fileInfo.setName(uuid);
      fileInfo.setOriginalFileName(fileName);
      fileInfo.setFileUrl(fileUrl);
      // TODO: hash data

      fileInfo = fileRepo.save(fileInfo);
      return fileInfo;
    } catch (Exception e) {
      throw new RuntimeException("File upload failed", e);
    }
  }
  
  @Transactional
  public boolean deleteFileByName(String dirPath, String name) {
    fileRepo.deleteByName(name);

    Path filePath = Paths.get(dirPath, name);
    try {
      if (Files.exists(filePath)) {
        Files.delete(filePath);
        return true;
      }
      return false;
    } catch (IOException e) {
      return false;
    }
  }

  public byte[] compressImage(InputStream imageInputStream) throws IOException {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    float imageQuality = 0.3f;

    // Create the buffered image
    BufferedImage bufferedImage = ImageIO.read(imageInputStream);

    // Get image writers for PNG format
    Iterator<ImageWriter> imageWriters = ImageIO.getImageWritersByFormatName("png");

    if (!imageWriters.hasNext())
      throw new IllegalStateException("Writers Not Found!!");

    ImageWriter imageWriter = imageWriters.next();
    try (ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(outputStream)) {
      imageWriter.setOutput(imageOutputStream);

      ImageWriteParam imageWriteParam = imageWriter.getDefaultWriteParam();
      imageWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
      imageWriteParam.setCompressionQuality(imageQuality);

      // Compress and insert the image into the byte array.
      imageWriter.write(null, new IIOImage(bufferedImage, null, null), imageWriteParam);
    } finally {
      imageWriter.dispose();
    }

    // Return compressed image bytes
    return outputStream.toByteArray();
  }
 

  public String getFileName(MultivaluedMap<String, String> header) {
    String[] contentDisposition = header.getFirst("Content-Disposition").split(";");
    for (String filename : contentDisposition) {
      if ((filename.trim().startsWith("filename"))) {
        String[] name = filename.split("=");
        String finalFileName = name[1].trim().replaceAll("\"", "");
        return finalFileName;
      }
    }
    return "unknown";
  }

  public void deleteFileEntity(FileEntity oldFileInfo, String filePath) {
    if (oldFileInfo != null) {
      deleteFileByName(filePath, oldFileInfo.getName());
      fileRepo.deleteByName(oldFileInfo.getName());
    }
  }
}