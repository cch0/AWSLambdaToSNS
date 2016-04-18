package cch.aws.lambda;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;

import org.joda.time.DateTime;

public class Handler {

    public String handle(Request request) {
        DateTime dateTime = DateTime.now();

        send(request.getMessage());

        return dateTime.toString();
    }

    private void send(String message) {
        AmazonSNSClient amazonSNSClient = amazonSNSClient(Regions.US_WEST_2);

        String topicArn = "get-the-topic-arn";

        PublishRequest publishRequest = new PublishRequest(topicArn, message);
        PublishResult publishResult = amazonSNSClient.publish(publishRequest);

    }

    private AmazonSNSClient amazonSNSClient(Regions regions) {
        AWSCredentials credentials = new BasicAWSCredentials("fill-the-blank",
            "fill-the-blank");

        AmazonSNSClient amazonSNSClient = new AmazonSNSClient();
        amazonSNSClient.configureRegion(regions);
        return amazonSNSClient;
    }

}
