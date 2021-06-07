package com.lindar.stannp.utils;

import com.lindar.stannp.model.Recipient;
import com.lindar.stannp.model.StannpHelper;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvMapWriter;
import org.supercsv.io.ICsvMapWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CsvUtils {

    public static File createRecipientsCsv(List<Recipient> recipients) throws IOException {
        List<String> customFields = new ArrayList<>();

        if(!recipients.isEmpty()){
            Map<String, String> firstCustom = recipients.get(0).getCustom();
            if(firstCustom != null && !firstCustom.isEmpty()){
                customFields = new ArrayList<>(firstCustom.keySet());
            }
        }

        return createRecipientsCsv(recipients, customFields);
    }

    public static File createRecipientsCsv(List<Recipient> recipients, List<String> customFields) throws IOException {

        List<String> headers = getDefaultFields();
        List<String> fields = getDefaultFields();
        List<CellProcessor> cellProcessors = getDefaultCellProcessors();

        customFields.forEach(customField -> {
            headers.add(customField);
            fields.add(customField);
            cellProcessors.add(new Optional());
        });

        List<Map<String, String>> recipientsMapped = recipients.stream()
                .map(StannpHelper::recipientToRequestMap)
                .collect(Collectors.toList());



        File file = File.createTempFile("stannp-export", ".csv");

        ICsvMapWriter beanWriter = null;
        try {
            beanWriter = new CsvMapWriter(new FileWriter(file),
                                          CsvPreference.STANDARD_PREFERENCE);

            // write the header
            beanWriter.writeHeader(headers.toArray(new String[headers.size()]));

            CellProcessor[] cellProcessorsArr = cellProcessors.toArray(new CellProcessor[cellProcessors.size()]);
            String[] fieldsArr = fields.toArray(new String[fields.size()]);

            // write the beans
            for( final Map<String, String> recipient : recipientsMapped ) {
                beanWriter.write(recipient, fieldsArr, cellProcessorsArr);
            }

        }
        finally {
            if( beanWriter != null ) {
                beanWriter.close();
            }
        }

        return file;
    }

    private static List<String> getDefaultFields(){
        List<String> defaultFields = new ArrayList<>();
        defaultFields.add("title");
        defaultFields.add("firstname");
        defaultFields.add("lastname");
        defaultFields.add("address1");
        defaultFields.add("address2");
        defaultFields.add("address3");
        defaultFields.add("city");
        defaultFields.add("postcode");
        defaultFields.add("country");
        return defaultFields;
    }

    private static List<CellProcessor> getDefaultCellProcessors(){
        List<CellProcessor> defaultCellProcessors = new ArrayList<>();
        defaultCellProcessors.add(new Optional());
        defaultCellProcessors.add(new Optional());
        defaultCellProcessors.add(new Optional());
        defaultCellProcessors.add(new Optional());
        defaultCellProcessors.add(new Optional());
        defaultCellProcessors.add(new Optional());
        defaultCellProcessors.add(new Optional());
        defaultCellProcessors.add(new Optional());
        defaultCellProcessors.add(new Optional());
        return defaultCellProcessors;
    }
}
