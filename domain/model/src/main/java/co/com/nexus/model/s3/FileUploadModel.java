package co.com.nexus.model.s3;

public record FileUploadModel(
        String fileName,
        String contentType,
        long size,
        byte[] content
) {}
