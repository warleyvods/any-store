package com.wavods.anystore.gateways;


import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.wavods.anystore.domains.FileUpload;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static java.time.LocalDateTime.*;
import static java.util.UUID.randomUUID;

@Service
@RequiredArgsConstructor
public class FileUploadGateway {

	private static final Logger log = LoggerFactory.getLogger(FileUploadGateway.class);
	
	private final AmazonS3 amazonS3;

	@Value("${cloud.aws.bucket.name}")
	private String bucketName;

	public FileUpload fileUpload(final MultipartFile file) throws IOException {
		if (!bucketExists(bucketName)) {
			log.info("Bucket does not exist");
			return null;
		}

		final String fileName = generateFileName(file);
		final PutObjectRequest objectRequest = createPutObjectRequest(bucketName, file, fileName);

		try {
			amazonS3.putObject(objectRequest);
			String objectUrl = amazonS3.getUrl(bucketName, fileName).toString();
			log.info("Image uploaded successfully");

			return new FileUpload(fileName, file.getContentType(), file.getSize(), objectUrl, now());
		} catch (SdkClientException e) {
			log.error("File upload failed", e);
			return null;
		}
	}

	private boolean bucketExists(final String bucketName) {
		return amazonS3.doesBucketExist(bucketName);
	}

	private String generateFileName(final MultipartFile file) {
		return randomUUID() + file.getOriginalFilename();
	}

	private PutObjectRequest createPutObjectRequest(String bucketName, MultipartFile file, String fileName) throws IOException {
		final ObjectMetadata metadata = createObjectMetadata(file);
		return new PutObjectRequest(bucketName, fileName, file.getInputStream(), metadata)
				.withCannedAcl(CannedAccessControlList.PublicRead);
	}

	private ObjectMetadata createObjectMetadata(MultipartFile file) {
		final ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentType(file.getContentType());
		metadata.setContentLength(file.getSize());
		return metadata;
	}

	//TODO refatorar este método
//	public FileUpload downloadFile(String bucketName, String fileName) {
//		if (!amazonS3.doesBucketExist(bucketName)) {
//			log.error("No Bucket Found");
//			return null;
//		}
//		S3Object s3object = amazonS3.getObject(bucketName, fileName);
//		S3ObjectInputStream inputStream = s3object.getObjectContent();
//		FileUpload fileUpload = new FileUpload();
//		try {
//			fileUpload.setFile(FileCopyUtils.copyToByteArray(inputStream));
//			fileUpload.setFileName(s3object.getKey());
//			return fileUpload;
//		} catch (Exception e) {
//			return null;
//		}
//	}

	public void deleteFile(final String fileName) {
		if (!amazonS3.doesBucketExist(bucketName)) {
			log.error("No Bucket Found");
			return;
		}
		amazonS3.deleteObject(bucketName, fileName);
	}
}
