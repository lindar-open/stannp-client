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
public class CreateLetterRequest {

    @Getter
    private Recipient recipient;
    @Getter
    private Integer recipientId;
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
    private String addons;

    public interface RecipientStep {
        ContentStep recipient(Recipient recipient);
        ContentStep recipient(int recipientId);
    }

    public interface ContentStep {
        OptionsStep template(int templateId);
        OptionsStep pages(List<String> pages);
        OptionsStep page(String page);
        OptionsStep file(File file);
        OptionsStep file(URL fileUrl);
    }

    public interface OptionsStep extends BuildStep {
        OptionsStep test(boolean test);
        OptionsStep duplex(boolean duplex);
        OptionsStep background(URL backgroundImageUrl);
        OptionsStep background(File backgroundImage);
        OptionsStep addons(String addons);
    }

    public interface BuildStep {
        CreateLetterRequest build();
    }

    @Data
    public static class Steps implements RecipientStep, ContentStep, OptionsStep, BuildStep {

        private Recipient recipient;
        private Integer recipientId;
        private Integer templateId;
        private URL backgroundImageUrl;
        private File backgroundImageFile;
        private List<String> pages;
        private Boolean test;
        private File file;
        private URL fileUrl;
        private Boolean duplex;
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
        public Steps addons(String addons) {
            this.addons = addons;
            return this;
        }

        @Override
        public CreateLetterRequest build() {
            CreateLetterRequest request = new CreateLetterRequest();

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
