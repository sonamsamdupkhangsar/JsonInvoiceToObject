package me.sonam.temp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class JsonOutput {
    private String message;
    private List<Result> result = new ArrayList<>();
    private Map signed_urls = new HashMap();//SignedUrls signed_urls;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    public Map getSigned_urls() {
        return signed_urls;
    }

    public void setSigned_urls(Map signed_urls) {
        this.signed_urls = signed_urls;
    }
/*
    public SignedUrls getSigned_urls() {
        return signed_urls;
    }

    public void setSigned_urls(SignedUrls signed_urls) {
        this.signed_urls = signed_urls;
    }
*/
}

class SignedUrlsq {
    private List<Map> list = new ArrayList<>();

    public List<Map> getList() {
        return list;
    }

    public void setList(List<Map> list) {
        this.list = list;
    }

}
class UploadedMeta {
    private String original;
    private String original_compressed;
    private String thumbnail;
    private String acw_rotate_90;
    private String acw_rotate_180;
    private String acw_rotate_270;
    private String original_with_long_expiry;
}
class Result {
    private String message;
    private String input;
    private List<PredictionItem> prediction = new ArrayList<>();
    private int page;
    private String request_file_id;
    private String filepath;
    private String id;
    private int rotation;
    private String file_url;
    private String request_metadata;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public List<PredictionItem> getPrediction() {
        return prediction;
    }

    public void setPredictionItems(List<PredictionItem> prediction) {
        this.prediction = prediction;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getRequest_file_id() {
        return request_file_id;
    }

    public void setRequest_file_id(String request_file_id) {
        this.request_file_id = request_file_id;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getRotation() {
        return rotation;
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
    }

    public String getFile_url() {
        return file_url;
    }

    public void setFile_url(String file_url) {
        this.file_url = file_url;
    }

    public String getRequest_metadata() {
        return request_metadata;
    }

    public void setRequest_metadata(String request_metadata) {
        this.request_metadata = request_metadata;
    }
}


class PredictionItem {
    private String id;
    private String label;
    private int xmin;
    private int ymin;
    private int xmax;
    private int ymax;
    private BigDecimal score;
    private String ocr_text;
    private String type;
    private List<Cell> cells = new ArrayList<>();
    private String status;
    private int page_no;
    private String label_id;



    public List<Cell> getCells() {
        return cells;
    }

    public void setCells(List<Cell> cells) {
        this.cells = cells;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getXmin() {
        return xmin;
    }

    public void setXmin(int xmin) {
        this.xmin = xmin;
    }

    public int getYmin() {
        return ymin;
    }

    public void setYmin(int ymin) {
        this.ymin = ymin;
    }

    public int getXmax() {
        return xmax;
    }

    public void setXmax(int xmax) {
        this.xmax = xmax;
    }

    public int getYmax() {
        return ymax;
    }

    public void setYmax(int ymax) {
        this.ymax = ymax;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public String getOcr_text() {
        return ocr_text;
    }

    public void setOcr_text(String ocr_text) {
        this.ocr_text = ocr_text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPage_no() {
        return page_no;
    }

    public void setPage_no(int page_no) {
        this.page_no = page_no;
    }

    public String getLabel_id() {
        return label_id;
    }

    public void setLabel_id(String label_id) {
        this.label_id = label_id;
    }
}
class Cell {
    private String id;
    private int row;
    private int col;
    private int row_span;
    private int col_span;

    private String label;
    private int xmin;
    private int ymin;
    private int xmax;
    private int ymax;
    private BigDecimal score;
    private String text;
    private String row_label;
    private String verification_status;
    private String status;
    private String failed_validation;
    private String label_id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow_span() {
        return row_span;
    }

    public void setRow_span(int row_span) {
        this.row_span = row_span;
    }

    public int getCol_span() {
        return col_span;
    }

    public void setCol_span(int col_span) {
        this.col_span = col_span;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getXmin() {
        return xmin;
    }

    public void setXmin(int xmin) {
        this.xmin = xmin;
    }

    public int getYmin() {
        return ymin;
    }

    public void setYmin(int ymin) {
        this.ymin = ymin;
    }

    public int getXmax() {
        return xmax;
    }

    public void setXmax(int xmax) {
        this.xmax = xmax;
    }

    public int getYmax() {
        return ymax;
    }

    public void setYmax(int ymax) {
        this.ymax = ymax;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getRow_label() {
        return row_label;
    }

    public void setRow_label(String row_label) {
        this.row_label = row_label;
    }

    public String getVerification_status() {
        return verification_status;
    }

    public void setVerification_status(String verification_status) {
        this.verification_status = verification_status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFailed_validation() {
        return failed_validation;
    }

    public void setFailed_validation(String failed_validation) {
        this.failed_validation = failed_validation;
    }

    public String getLabel_id() {
        return label_id;
    }

    public void setLabel_id(String label_id) {
        this.label_id = label_id;
    }
}
