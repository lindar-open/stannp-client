package com.lindar.stannp.model.request;

import com.lindar.stannp.model.Recipient;
import com.lindar.stannp.model.enums.PostcardSize;
import lombok.Data;
import lombok.Getter;
import org.apache.commons.beanutils.BeanUtils;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;

@Data
public class CreatePostcardRequest {

    @Getter
    private Recipient recipient;
    @Getter
    private Integer recipientId;
    @Getter
    private Integer templateId;
    @Getter
    private PostcardSize size;
    @Getter
    private URL frontImageUrl;
    @Getter
    private File frontImageFile;
    @Getter
    private URL signatureImageUrl;
    @Getter
    private File signatureImageFile;
    @Getter
    private URL backImageUrl;
    @Getter
    private File backImageFile;
    @Getter
    private String message;
    @Getter
    private Boolean test;
    @Getter
    private String addons;

    public interface RecipientStep {
        ContentStep recipient(Recipient recipient);
        ContentStep recipient(int recipientId);
    }

    public interface ContentStep {
        OptionsStep template(int templateId);
        ManualStep manual(PostcardSize size, URL frontImageUrl);
        ManualStep manual(PostcardSize size, File frontImage);
    }

    public interface ManualStep extends BuildStep {
        ManualStep signature(File signatureImage);
        ManualStep signature(URL signatureImageUrl);

        ManualStep back(File backImage);
        ManualStep back(URL backImageUrl);

        ManualStep message(String message);
        OptionsStep options();
    }

    public interface OptionsStep extends BuildStep {
        OptionsStep test(boolean test);
        OptionsStep addons(String addons);
    }

    public interface BuildStep {
        CreatePostcardRequest build();
    }

    @Data
    public static class Steps implements RecipientStep, ContentStep, ManualStep, OptionsStep, BuildStep {

        private Recipient recipient;
        private Integer recipientId;
        private Integer templateId;
        private PostcardSize size;
        private URL frontImageUrl;
        private File frontImageFile;
        private URL signatureImageUrl;
        private File signatureImageFile;
        private URL backImageUrl;
        private File backImageFile;
        private String message;
        private Boolean test;
        private String addons;

        private Steps(){}

        @Override
        public Steps recipient(Recipient recipient) {
            this.recipient = recipient;
            return this;
        }

        @Override
        public Steps recipient(int recipientId) {
            this.recipientId = recipientId;
            return this;
        }

        @Override
        public Steps template(int templateId) {
            this.templateId = templateId;
            return this;
        }

        @Override
        public Steps manual(PostcardSize size, URL frontImageUrl) {
            this.size = size;
            this.frontImageUrl = frontImageUrl;
            return this;
        }

        @Override
        public Steps manual(PostcardSize size, File frontImageFile) {
            this.size = size;
            this.frontImageFile = frontImageFile;
            return this;
        }

        @Override
        public ManualStep signature(File signatureImage) {
            this.signatureImageFile = signatureImage;
            return this;
        }

        @Override
        public Steps signature(URL signatureImageUrl) {
            this.signatureImageUrl = signatureImageUrl;
            return this;
        }

        @Override
        public Steps back(File backImage) {
            this.backImageFile = backImage;
            return this;
        }

        @Override
        public Steps back(URL backImageUrl) {
            this.backImageUrl = backImageUrl;
            return this;
        }

        @Override
        public Steps message(String message) {
            this.message = message;
            return this;
        }

        @Override
        public Steps options() {
            return this;
        }

        @Override
        public Steps test(boolean test) {
            this.test = test;
            return this;
        }

        @Override
        public Steps addons(String addons) {
            this.addons = addons;
            return this;
        }

        @Override
        public CreatePostcardRequest build() {
            CreatePostcardRequest request = new CreatePostcardRequest();

            try {
                BeanUtils.copyProperties(request, this);
            } catch (IllegalAccessException | InvocationTargetException ignored) {}

            return request;
        }
    }

    public static RecipientStep newBuilder() {
        return new Steps();
    }

}
