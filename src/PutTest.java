/*
 * Copyright 2010-2012 Amazon.com, Inc. or its affiliates. All Rights
 Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is
 distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 either
 * express or implied. See the License for the specific language
 governing
 * permissions and limitations under the License.
 */
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

/**
 * This sample demonstrates how to make basic requests to Amazon S3 using the
 * AWS SDK for Java.
 * <p>
 * <b>Prerequisites:</b> You must have a valid Amazon Web Services developer
 * account, and be signed up to use Amazon S3. For more information on Amazon
 * S3, see http://aws.amazon.com/s3.
 * <p>
 * <b>Important:</b> Be sure to fill in your AWS access credentials in the
 * AwsCredentials.properties file before you try to run this sample.
 * http://aws.amazon.com/security-credentials
 */
public class PutTest {

	public static void main(String[] args) throws IOException {
		/*
		 * Important: Be sure to fill in your AWS access credentials in the
		 * AwsCredentials.properties file before you try to run this sample.
		 * http://aws.amazon.com/security-credentials
		 */
		AmazonS3 s3 = new AmazonS3Client(new PropertiesCredentials(

		PutTest.class.getResourceAsStream("AwsCredentials.properties")));

	//	String bucketNameNew = "my-first-s3-bucket-" + UUID.randomUUID();
		String bucketName = "dtccTest";
				
		String key = "largerfile.txt";

		System.out.println("===========================================");
		System.out.println("Amazon S3 put ");
		System.out.println("===========================================\n");

		try {

			/*
			 * List the buckets in your account
			 */
			System.out.println("Listing buckets");
			for (Bucket bucket : s3.listBuckets()) {
				System.out.println(" - " + bucket.getName());
			}
			System.out.println();

			System.out.println("Uploading a new object to S3 from a file\n");
			byte[] ba = String.valueOf(createSampleString()).getBytes();
			InputStream is = new ByteArrayInputStream (ba);
			ObjectMetadata md = new ObjectMetadata();
			long contentLength=ba.length;//70
			md.setContentLength(contentLength);
			md.setContentType("text/plain");
			PutObjectRequest request = new PutObjectRequest(bucketName, key, is, md);
			request.setCannedAcl(CannedAccessControlList.PublicRead);
			s3.putObject(request);

		} catch (AmazonServiceException ase) {
			System.out
					.println("Caught an AmazonServiceException, whichmeans your request made it "
							+ "to Amazon S3, but was rejected with an errorresponse for some reason.");
			System.out.println("Error Message:    " + ase.getMessage());
			System.out.println("HTTP Status Code: " + ase.getStatusCode());
			System.out.println("AWS Error Code:   " + ase.getErrorCode());
			System.out.println("Error Type:       " + ase.getErrorType());
			System.out.println("Request ID:       " + ase.getRequestId());
		} catch (AmazonClientException ace) {
			System.out
					.println("Caught an AmazonClientException, whichmeans the client encountered "
							+ "a serious internal problem while trying tocommunicate with S3, "
							+ "such as not being able to access the network.");
			System.out.println("Error Message: " + ace.getMessage());
		}
	}




	private static StringBuffer createSampleString() {
	

		StringBuffer sb = new StringBuffer();
		for (int i = 1; i <= 10; i = i + 1) {

			sb.append("SpTest\n");

		}
		

		return sb;
	}


}