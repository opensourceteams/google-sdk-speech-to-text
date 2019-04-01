package com.opensourceteams.module.google.speech.to.text.simple.multiChannel;

import com.google.cloud.speech.v1.*;
import com.google.protobuf.ByteString;
import org.apache.log4j.Logger;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * - 美国英语 (en-US) 或英国英语 (en-GB)
 * - 普通话 (中国大陆)	cmn-Hans-CN	中文、普通话（中国简体）
 */
public class RunEnglish {

    static Logger logger = Logger.getLogger(RunEnglish.class);
    public static void main(String[] args) throws Exception {
        logger.info("开始");
        transcribeMultiChannel("data/wav/english/commercial_stereo.wav");
           //两个音轨，分离两路对话，可惜中文不支持,多轨道也变成单轨
        //transcribeMultiChannel("data/wav/cn/2人2轨-36秒.wav");
        logger.info("结束");

    }



    /**
     * Transcribe a local audio file with multi-channel recognition
     *
     * @param fileName the path to local audio file
     */
    public static void transcribeMultiChannel(String fileName) throws Exception {
        Path path = Paths.get(fileName);
        byte[] content = Files.readAllBytes(path);

        try (SpeechClient speechClient = SpeechClient.create()) {
            // Get the contents of the local audio file
            RecognitionAudio recognitionAudio =
                    RecognitionAudio.newBuilder().setContent(ByteString.copyFrom(content)).build();

            // Configure request to enable multiple channels
            RecognitionConfig config =
                    RecognitionConfig.newBuilder()
                           // .setEncoding(RecognitionConfig.AudioEncoding.LINEAR16)
                            .setLanguageCode("en-US")
                           // .setSampleRateHertz(16000)
                            .setAudioChannelCount(2)
                            .setEnableSeparateRecognitionPerChannel(true)
                            .build();

            // Perform the transcription request
            RecognizeResponse recognizeResponse = speechClient.recognize(config, recognitionAudio);

            // Print out the results
            for (SpeechRecognitionResult result : recognizeResponse.getResultsList()) {
                // There can be several alternative transcripts for a given chunk of speech. Just use the
                // first (most likely) one here.
                SpeechRecognitionAlternative alternative = result.getAlternatives(0);
                System.out.format("Transcript : %s\n", alternative.getTranscript());
                System.out.printf("Channel Tag : %s\n", result.getChannelTag());
            }
        }
    }
}
