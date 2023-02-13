package me.sonam.temp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class JsonToVendorType {
    private static final Logger LOG = LoggerFactory.getLogger(JsonToVendorType.class);

    public Vendor buildVendorData(File file) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonOutput jsonOutput = objectMapper.readValue(file, JsonOutput.class);
            StringBuilder stringBuilder = new StringBuilder();

            Vendor vendor = new Vendor();
            vendor.setInvoice( new Invoice());

            jsonOutput.getResult().forEach(result -> {
                result.getPrediction().forEach(predictionItem -> {
                    if (predictionItem.getLabel().equals("seller_website")) {
                        vendor.setWebAddress(predictionItem.getOcr_text());
                    }
                    else if (predictionItem.getLabel().equals("seller_address")) {
                        vendor.setAddress(predictionItem.getOcr_text());
                    }
                    else if (predictionItem.getLabel().equals("seller_phone")) {
                        vendor.setPhone(predictionItem.getOcr_text());
                    }
                    else if (predictionItem.getLabel().equals("seller_fax_number")) {
                        vendor.setFax(predictionItem.getOcr_text())
                        ;                    }
                    else if (predictionItem.getLabel().equals("invoice_date")) {
                        vendor.getInvoice().setInvoiceDate(predictionItem.getOcr_text());
                    }
                    else if (predictionItem.getLabel().equals("invoice_number")) {
                        vendor.getInvoice().setInvoiceNumber(predictionItem.getOcr_text());
                    }
                    else if (predictionItem.getLabel().equals("po_number")) {
                        vendor.getInvoice().addPurchaseOrderNumber(predictionItem.getOcr_text());
                    }
                    else if (predictionItem.getLabel().equals("buyer_name")) {
                        vendor.getInvoice().getBuyer().setName(predictionItem.getOcr_text());
                    }
                    else if (predictionItem.getLabel().equals("buyer_address")) {
                        vendor.getInvoice().getBuyer().setShipToAddress(predictionItem.getOcr_text());
                    }
                    else if (predictionItem.getLabel().equals("invoice_amount")) {
                        vendor.getInvoice().setAmount(new BigDecimal(predictionItem.getOcr_text()));
                    }
                    else if (predictionItem.getLabel().equals("seller_email")) {
                        vendor.setEmail(predictionItem.getOcr_text());
                    }
                    else if (predictionItem.getLabel().equals("client_id")) {
                        vendor.getInvoice().getBuyer().setCustomerId(predictionItem.getOcr_text());
                    }
                    else if (predictionItem.getLabel().equals("shipto_address")) {
                        vendor.getInvoice().getBuyer().setShipToAddress(predictionItem.getOcr_text());
                    }
                    else if (predictionItem.getLabel().equals("total_due_amount")) {
                        vendor.getInvoice().setAmount(new BigDecimal(predictionItem.getOcr_text()));
                    }
                    else if (predictionItem.getLabel().equals("payment_due_date")) {
                        vendor.getInvoice().setDueDate(predictionItem.getOcr_text());
                    }
                    else if (predictionItem.getLabel().equals("seller_name")) {
                        vendor.setName(predictionItem.getOcr_text());
                    }
                    else if (predictionItem.getLabel().equals("seller_address")) {
                        vendor.setAddress(predictionItem.getOcr_text());
                    }
                    else if (predictionItem.getLabel().equals("table")) {
                        if (predictionItem.getOcr_text().equals("table") && predictionItem.getCells().size() > 0) {
                            InvoiceDetail invoiceDetail = new InvoiceDetail();
                            int row = 0;
                            int col = 0;
                            List<Cell> cells = predictionItem.getCells();

                            for(Cell cell: cells) {
                                if (cell.getCol() > col) {
                                    col = cell.getCol();
                                }
                                if (cell.getRow() > row) {
                                    row = cell.getRow();
                                    vendor.getInvoice().getInvoiceDetails().add(new InvoiceDetail());
                                    LOG.info("added invoiceDetail: row: {}, col: {}", row, col);
                                }

                                if (cell.getLabel().equals("Description")) {
                                    LOG.info("cell.getText: '{}'", cell.getText());
                                    if (cell.getText().isEmpty() || cell.getText().equals("null") || cell.getText().trim().equals("")) {
                                        LOG.info("break");
                                        vendor.getInvoice().getInvoiceDetails().remove(row-1);
                                        break;
                                    }
                                    else {
                                        vendor.getInvoice().getInvoiceDetails().get(row-1).setDescription(cell.getText());
                                    }
                                }
                                else if (cell.getLabel().equals("Quantity")) {
                                    if (!cell.getText().isEmpty()) {
                                        vendor.getInvoice().getInvoiceDetails().get(row - 1).setQuantity(Integer.parseInt(cell.getText()));
                                    }
                                }
                                else if (cell.getLabel().equals("Price")) {
                                    if (!cell.getText().isEmpty()) {
                                        vendor.getInvoice().getInvoiceDetails().get(row-1).setPrice(new BigDecimal(cell.getText()));
                                    }
                                }
                                else if (cell.getLabel().equals("Line_Amount")) {
                                    if (!cell.getText().isEmpty()) {
                                        vendor.getInvoice().getInvoiceDetails().get(row-1).setAmount(new BigDecimal(cell.getText()));
                                    }
                                }
                            }
                        }
                    }
                });


            });

            LOG.info("vendor: {}", vendor);
            return vendor;
        }
        catch(Exception e) {
            LOG.error("exception occured", e);
            return null;
        }
    }
}



class Vendor {

    private String name;
    private String address;
    private String city;
    private String zipcode;
    private String webAddress;
    private String phone;
    private String fax;
    private String email;

    private Invoice invoice;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public String getWebAddress() {
        return webAddress;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public Vendor() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public void setWebAddress(String webAddress) {
        this.webAddress = webAddress;
    }

    @Override
    public String toString() {
        return "Vendor{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", webAddress='" + webAddress + '\'' +
                ", phone='" + phone + '\'' +
                ", fax='" + fax + '\'' +
                ", email='" + email + '\'' +
                ", invoice=" + invoice +
                '}';
    }
}
class Buyer {
    private String customerId;
    private String orderedBy;
    private String name;
    private String shipToAddress;

    public String getName() {
        return name;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getOrderedBy() {
        return orderedBy;
    }

    public void setOrderedBy(String orderedBy) {
        this.orderedBy = orderedBy;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShipToAddress() {
        return shipToAddress;
    }

    public void setShipToAddress(String shipToAddress) {
        this.shipToAddress = shipToAddress;
    }

    @Override
    public String toString() {
        return "Buyer{" +
                "customerId='" + customerId + '\'' +
                ", orderedBy='" + orderedBy + '\'' +
                ", name='" + name + '\'' +
                ", shipToAddress='" + shipToAddress + '\'' +
                '}';
    }
}
class Invoice {
    private String customerNumber;
    private String orderedBy;
    private String orderNumber;
    private List<String> purchaseOrderNumbers = new ArrayList<>();

    private String invoiceDate;
    private String invoiceNumber;
    private BigDecimal amount;

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    private String dueDate;
    private Buyer buyer = new Buyer();

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setPurchaseOrderNumbers(List<String> purchaseOrderNumbers) {
        this.purchaseOrderNumbers = purchaseOrderNumbers;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    private List<InvoiceDetail> invoiceDetails = new ArrayList<>();

    public void addPurchaseOrderNumber(String poNumber) {
        purchaseOrderNumbers.add(poNumber);
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getOrderedBy() {
        return orderedBy;
    }

    public void setOrderedBy(String orderedBy) {
        this.orderedBy = orderedBy;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public List<String> getPurchaseOrderNumbers() {
        return purchaseOrderNumbers;
    }

    public void setPurchaseOrderNumber(List<String> purchaseOrderNumbers) {
        this.purchaseOrderNumbers = purchaseOrderNumbers;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public List<InvoiceDetail> getInvoiceDetails() {
        return invoiceDetails;
    }

    public void setInvoiceDetails(List<InvoiceDetail> invoiceDetails) {
        this.invoiceDetails = invoiceDetails;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "customerNumber='" + customerNumber + '\'' +
                ", orderedBy='" + orderedBy + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", purchaseOrderNumbers=" + purchaseOrderNumbers +
                ", invoiceDate=" + invoiceDate +
                ", invoiceNumber=" + invoiceNumber +
                ", amount=" + amount +
                ", dueDate=" + dueDate +
                ", buyer=" + buyer +
                ", invoiceDetails=" + invoiceDetails +
                '}';
    }
}

class InvoiceDetail {
    private String description;
    private int quantity;
    private BigDecimal price;
    private BigDecimal amount;

    @Override
    public String toString() {
        return "InvoiceDetail{" +
                "description='" + description + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", amount=" + amount +
                '}';
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}

