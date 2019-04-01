# google 语音转文本(多轨支持)
- 多轨音频，能按轨道进行识别(案例，采访时多个人对话，一人一句，能区分对象是哪个人在说话)
- 经测试，不支持中文
- 依赖就是音频文件声音，也是由轨道组成，录制时，多个人，分别一个轨道就行

## 源码
- https://github.com/opensourceteams/google-sdk-speech-to-text


## 案例
```aidl
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
public class RunEnglish2 {

    static Logger logger = Logger.getLogger(RunEnglish2.class);
    public static void main(String[] args) throws Exception {
        logger.info("开始");
           //两个音轨，分离两路对话，可惜中文不支持,多轨道也变成单轨
        //transcribeMultiChannel("data/wav/english/2人2轨-36秒.wav");
        transcribeMultiChannel("data/wav/english/2人2轨-55秒.wav");
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
                            //.setSampleRateHertz(16000)
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

```

## 输出结果
```aidl
19-04-01 16:40:43,484 INFO [com.opensourceteams.module.google.speech.to.text.simple.multiChannel.RunEnglish2] - 开始
Transcript : thank you for coming in today and I'm happy to help you with your preparing for your interview why don't we start by having you tell me a little bit about yourself
Channel Tag : 2
Transcript : so I'm a senior Communications major hear arcari graduating anticipated graduation is May of 2015 I have very strong points in working with people I am very involved here at cardi so I'm a part of the Student Activities office I'm a student worker there
Channel Tag : 1
Transcript :  thank you for coming in today and I'm happy to help you with your preparing for your interview why don't we start by having you tell me a little bit about yourself
Channel Tag : 2
Transcript :  so I'm a senior Communications major here at kauri graduating anticipated graduation is May of 2015 I have very strong points in working with people I am very involved here at cardi so I'm a part of the Student Activities office I'm a student worker there
Channel Tag : 1
2019-04-01 16:41:03,215 INFO [com.opensourceteams.module.google.speech.to.text.simple.multiChannel.RunEnglish2] - 结束

```