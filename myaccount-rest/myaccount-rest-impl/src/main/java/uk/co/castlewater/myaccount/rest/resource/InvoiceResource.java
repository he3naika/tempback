package uk.co.castlewater.myaccount.rest.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.co.castlewater.myaccount.rest.api.resource.InvoiceResourceApi;
import uk.co.castlewater.myaccount.service.api.model.Invoice;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Anatol Sialitski
 */
@RestController
@RequestMapping("/invoices")
public class InvoiceResource implements InvoiceResourceApi {

    @Override
    @GetMapping
    public List<Invoice> getInvoicesForCurrentAccount() {
        //throw new UnsupportedOperationException();
        return createDummyInvoiceList();
    }

    @Override
    @GetMapping(value = "/{invoiceNumber}")
    public void getInvoiceAsAttachment(@PathVariable("invoiceNumber") String invoiceNumber) {
        throw new UnsupportedOperationException();
    }

    private List<Invoice> createDummyInvoiceList() {
        List<Invoice> invoiceList = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            invoiceList.add(createDummyInvoice("1234567" + i));
        }
        return invoiceList;
    }

    private Invoice createDummyInvoice(String invoiceNumber) {
        Invoice invoice = new Invoice();
        invoice.setId(invoiceNumber);
        return invoice;
    }


}
