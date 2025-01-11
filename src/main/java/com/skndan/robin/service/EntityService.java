package com.skndan.robin.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skndan.robin.entity.FileEntity;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MultivaluedMap;

@ApplicationScoped
public class EntityService {

  @Inject
  FileService fileService;

  public <T> T processMultipartRequest(MultipartFormDataInput input, Class<T> entityClass)
      throws Exception {
    Map<String, List<InputPart>> formParts = input.getFormDataMap();

    // Process entity data
    List<InputPart> dataParts = formParts.get("data");
    String data = dataParts.get(0).getBodyAsString();
    T entity = new ObjectMapper().readValue(data, entityClass);

    return entity;
  }

  public FileEntity extractFile(MultipartFormDataInput input, String filePath) {

    Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
    List<InputPart> inputFileParts = uploadForm.get("file");

    FileEntity fileInfo = null;

    InputPart inputPart = inputFileParts.get(0);

    try {

      MultivaluedMap<String, String> header = inputPart.getHeaders();

      String fileName = fileService.getFileName(header);

      InputStream inputStream = inputPart.getBody(InputStream.class, null);

      fileInfo = fileService.uploadFile(filePath, fileName, inputStream);

    } catch (Exception e) {
      e.printStackTrace();
    }

    return fileInfo;
  }

  public List<FileEntity> extractFileList(MultipartFormDataInput input, String filePath) {

    Map<String, List<InputPart>> uploadForm = input.getFormDataMap();

    List<InputPart> inputFileParts = uploadForm.get("file");

    FileEntity fileInfo = new FileEntity();

    List<FileEntity> fileList = new ArrayList<>();

    for (InputPart inputPart : inputFileParts) {

      try {

        MultivaluedMap<String, String> header = inputPart.getHeaders();

        String fileName = fileService.getFileName(header);

        InputStream inputStream = inputPart.getBody(InputStream.class, null);

        fileInfo = fileService.uploadFile(filePath, fileName, inputStream);

        fileList.add(fileInfo);

      } catch (Exception e) {
        e.printStackTrace();
      }

    }
    return fileList;
  }
}
