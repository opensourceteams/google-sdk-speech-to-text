package com.opensourceteams.module.google.speech.to.text.simple.asynLlong;


import com.google.api.gax.longrunning.OperationFuture;
import com.google.cloud.speech.v1.LongRunningRecognizeMetadata;
import com.google.cloud.speech.v1.LongRunningRecognizeResponse;
import com.google.cloud.speech.v1.SpeechRecognitionAlternative;
import com.google.cloud.speech.v1.SpeechRecognitionResult;
import com.google.protobuf.ByteString;
import org.apache.log4j.Logger;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

/**
 * - 美国英语 (en-US) 或英国英语 (en-GB)
 * - 普通话 (中国大陆)	cmn-Hans-CN	中文、普通话（中国简体）
 */
public class RunCN {

    static Logger logger = Logger.getLogger(RunCN.class);
    public static void main(String[] args) throws Exception {
        logger.info("开始");
        //限制10M
        transcribeSyncLong("data/wav/cn/早饭吃西红柿炒鸡蛋.wav"); //ok
        //transcribeSyncLong("/Users/liuwen/Downloads/temp/big/丧心病狂的谷歌语音识别-3分55秒.pcm");
        logger.info("结束");

    }





    /**
     * Performs speech recognition on raw PCM audio and prints the transcription.
     *
     * @param fileName the path to a PCM audio file to transcribe.
     */
    public static void transcribeSyncLong(String fileName) throws Exception {
        try (com.google.cloud.speech.v1.SpeechClient speech = com.google.cloud.speech.v1.SpeechClient.create()) {
            Path path = Paths.get(fileName);
            byte[] data = Files.readAllBytes(path);
            ByteString audioBytes = ByteString.copyFrom(data);

            // Configure request with local raw PCM audio
            com.google.cloud.speech.v1.RecognitionConfig config =
                    com.google.cloud.speech.v1.RecognitionConfig.newBuilder()
                             .setEncoding(com.google.cloud.speech.v1.RecognitionConfig.AudioEncoding.LINEAR16)
                            .setLanguageCode("cmn-Hans-CN")
                            .setSampleRateHertz(16000)
                            .build();
            com.google.cloud.speech.v1.RecognitionAudio audio = com.google.cloud.speech.v1.RecognitionAudio.newBuilder().setContent(audioBytes).build();

            // Use non-blocking call for getting file transcription
            OperationFuture<LongRunningRecognizeResponse, LongRunningRecognizeMetadata> response =
                    speech.longRunningRecognizeAsync(config, audio);


            while (!response.isDone()) {
                System.out.println("Waiting for response..." + new Date());
                logger.debug("等待服务端响应......");

                Thread.sleep(10000);
            }

            for (SpeechRecognitionResult result : response.get().getResultsList()) {

                // There can be several alternative transcripts for a given chunk of speech. Just use the
                // first (most likely) one here.
                SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);

                // Print out the result
                System.out.printf("输出结果 : %s\n\n", alternative.getTranscript());
            }
        }
    }


}
