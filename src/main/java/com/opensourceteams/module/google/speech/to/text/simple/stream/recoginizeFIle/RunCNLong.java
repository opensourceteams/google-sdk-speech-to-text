package com.opensourceteams.module.google.speech.to.text.simple.stream.recoginizeFIle;

import com.google.api.gax.rpc.ApiStreamObserver;
import com.google.api.gax.rpc.BidiStreamingCallable;
import com.google.cloud.speech.v1.*;
import com.google.common.util.concurrent.SettableFuture;
import com.google.protobuf.ByteString;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class RunCNLong {

    static Logger logger = Logger.getLogger(RunCNLong.class);

    public static void main(String[] args) throws Exception {

        logger.info("开始");
        //String path = "data/wav/cn/早饭吃西红柿炒鸡蛋.wav" ;//这种方式不支持长语音
        String path = "/Users/liuwen/Downloads/temp/big/丧心病狂的谷歌语音识别-3分55秒.pcm" ;
        streamingRecognizeFile(path);
        logger.info("结束");
    }

    // [END speech_transcribe_async_gcs]

    // [START speech_transcribe_streaming]
    /**
     * Performs streaming speech recognition on raw PCM audio data.
     *
     * @param fileName the path to a PCM audio file to transcribe.
     */
    public static void streamingRecognizeFile(String fileName) throws Exception, IOException {
        Path path = Paths.get(fileName);
        byte[] data = Files.readAllBytes(path);

        // Instantiates a client with GOOGLE_APPLICATION_CREDENTIALS
        try (SpeechClient speech = SpeechClient.create()) {

            // Configure request with local raw PCM audio
            RecognitionConfig recConfig =
                    RecognitionConfig.newBuilder()
                            .setEncoding(RecognitionConfig.AudioEncoding.LINEAR16)
                            .setLanguageCode("cmn-Hans-CN")
                            .setSampleRateHertz(16000)
                            .setModel("default")
                            .build();
            StreamingRecognitionConfig config =
                    StreamingRecognitionConfig.newBuilder().setConfig(recConfig).build();

            class ResponseApiStreamingObserver<T> implements ApiStreamObserver<T> {
                private final SettableFuture<List<T>> future = SettableFuture.create();
                private final List<T> messages = new java.util.ArrayList<T>();

                @Override
                public void onNext(T message) {
                    messages.add(message);
                }

                @Override
                public void onError(Throwable t) {
                    future.setException(t);
                }

                @Override
                public void onCompleted() {
                    future.set(messages);
                }

                // Returns the SettableFuture object to get received messages / exceptions.
                public SettableFuture<List<T>> future() {
                    return future;
                }
            }

            ResponseApiStreamingObserver<StreamingRecognizeResponse> responseObserver =
                    new ResponseApiStreamingObserver<>();

            BidiStreamingCallable<StreamingRecognizeRequest, StreamingRecognizeResponse> callable =
                    speech.streamingRecognizeCallable();

            ApiStreamObserver<StreamingRecognizeRequest> requestObserver =
                    callable.bidiStreamingCall(responseObserver);

            // The first request must **only** contain the audio configuration:
            requestObserver.onNext(
                    StreamingRecognizeRequest.newBuilder().setStreamingConfig(config).build());

            // Subsequent requests must **only** contain the audio data.
            requestObserver.onNext(
                    StreamingRecognizeRequest.newBuilder()
                            .setAudioContent(ByteString.copyFrom(data))
                            .build());

            // Mark transmission as completed after sending the data.
            requestObserver.onCompleted();

            List<StreamingRecognizeResponse> responses = responseObserver.future().get();

            for (StreamingRecognizeResponse response : responses) {
                // For streaming recognize, the results list has one is_final result (if available) followed
                // by a number of in-progress results (if iterim_results is true) for subsequent utterances.
                // Just print the first result here.
                StreamingRecognitionResult result = response.getResultsList().get(0);
                // There can be several alternative transcripts for a given chunk of speech. Just use the
                // first (most likely) one here.
                SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
                System.out.printf("Transcript : %s\n", alternative.getTranscript());
            }
        }
    }
}
