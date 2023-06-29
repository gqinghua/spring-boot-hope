
package com.data.hope.core.common;


import com.data.hope.core.base.consts.ValueType;
import com.data.hope.core.base.exception.Exceptions;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class CSVParse {

    private String path;

    private ValueType[] types;

    private SimpleDateFormat simpleDateFormat;

    public static CSVParse create(String path, ParseConfig parseConfig) {
        CSVParse csvParse = new CSVParse();
        csvParse.path = path;
        csvParse.simpleDateFormat = new SimpleDateFormat(parseConfig.getDateFormat());
        return csvParse;
    }

    public static CSVParse create(String path) {
        CSVParse csvParse = new CSVParse();
        csvParse.path = path;
        return csvParse;
    }

    public List<List<Object>> parse() throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            Exceptions.notFound(path);
        }
        List<CSVRecord> records = CSVParser.parse(file, StandardCharsets.UTF_8, CSVFormat.DEFAULT).getRecords();
        if (CollectionUtils.isEmpty(records)) {
            return Collections.emptyList();
        }
        if (records.size() < 2) {
            types = inferDataType(records.get(0));
        } else {
            types = inferDataType(records.get(1));
        }
        List<List<Object>> values = records.parallelStream().map(this::extractValues)
                .collect(Collectors.toList());
        // remove utf-8-with-bom char
        String start = values.get(0).get(0).toString();
        if (start.charAt(0) == '\uFEFF') {
            values.get(0).set(0, start.substring(1));
        }
        return values;
    }

    private ValueType[] inferDataType(CSVRecord record) {
        ValueType[] valueTypes = new ValueType[record.size()];
        for (int i = 0; i < record.size(); i++) {
            if (NumberUtils.isNumber(record.get(i))) {
                valueTypes[i] = ValueType.NUMERIC;
                continue;
            }
            try {
                simpleDateFormat.parse(record.get(i));
                valueTypes[i] = ValueType.DATE;
                continue;
            } catch (Exception ignore) {
            }
            valueTypes[i] = ValueType.STRING;
        }
        return valueTypes;
    }

    private List<Object> extractValues(CSVRecord record) {
        if (record == null || record.size() == 0) {
            return Collections.emptyList();
        }
        LinkedList<Object> values = new LinkedList<>();
        for (int i = 0; i < record.size(); i++) {
            values.add(record.get(i));
        }
        return values;
    }


    public static class ParseConfig {
        private String dateFormat;

        public String getDateFormat() {
            return dateFormat;
        }

        public void setDateFormat(String dateFormat) {
            this.dateFormat = dateFormat;
        }
    }

}
