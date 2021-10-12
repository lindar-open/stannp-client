package com.lindar.stannp.model.request;

import com.lindar.stannp.model.Recipient;
import lombok.Data;
import lombok.Getter;
import org.apache.commons.beanutils.BeanUtils;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Data
public class CreateBatchLetterRequest {

    @Getter
    private List<Recipient> recipients;
    @Getter
    private Integer templateId;
    @Getter
    private URL backgroundImageUrl;
    @Getter
    private File backgroundImageFile;
    @Getter
    private URL fileUrl;
    @Getter
    private File file;
    @Getter
    private Boolean duplex;
    @Getter
    private List<String> pages;
    @Getter
    private Boolean test;
    @Getter
    private Boolean postUnverified;

    public interface RecipientStep {
        ContentStep recipients(List<Recipient> recipients);
    }

    public interface ContentStep {
        OptionsStep template(int templateId);
        OptionsStep pages(List<String> pages);
        OptionsStep page(String page);
        OptionsStep file(File file);
        OptionsStep file(URL fileUrl);
    }

    public interface OptionsStep extends BuildStep {
        OptionsStep postUnverified(boolean postUnverified);
        OptionsStep test(boolean test);
        OptionsStep duplex(boolean duplex);
        OptionsStep background(URL backgroundImageUrl);
        OptionsStep background(File backgroundImage);
    }

    public interface BuildStep {
        CreateBatchLetterRequest build();
    }

    @Data
    public static class Steps implements RecipientStep, ContentStep, OptionsStep, BuildStep {

        private List<Recipient> recipients;
        private Integer recipientId;
        private Integer templateId;
        private URL backgroundImageUrl;
        private File backgroundImageFile;
        private List<String> pages;
        private Boolean test;
        private File file;
        private URL fileUrl;
        private Boolean duplex;
        private Boolean postUnverified;

        private Steps(){}

        @Override
        public Steps recipients(List<Recipient> recipients) {
            this.recipients = recipients;
            return this;
        }

        @Override
        public Steps template(int templateId) {
            this.templateId = templateId;
            return this;
        }

        @Override
        public Steps pages(List<String> pages) {
            this.pages = pages;
            return this;
        }

        @Override
        public Steps page(String page) {
            this.pages = new ArrayList<>();
            this.pages.add(page);
            return this;
        }

        @Override
        public Steps file(File file) {
            this.file = file;
            return this;
        }

        @Override
        public Steps file(URL fileUrl) {
            this.fileUrl = fileUrl;
            return this;
        }

        @Override
        public Steps test(boolean test) {
            this.test = test;
            return this;
        }

        @Override
        public Steps postUnverified(boolean postUnverified) {
            this.postUnverified = postUnverified;
            return this;
        }

        @Override
        public Steps duplex(boolean duplex) {
            this.duplex = duplex;
            return this;
        }

        @Override
        public Steps background(URL backgroundImageUrl) {
            this.backgroundImageUrl = backgroundImageUrl;
            return this;
        }

        @Override
        public Steps background(File backgroundImage) {
            this.backgroundImageFile = backgroundImage;
            return this;
        }

        @Override
        public CreateBatchLetterRequest build() {
            CreateBatchLetterRequest request = new CreateBatchLetterRequest();

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
