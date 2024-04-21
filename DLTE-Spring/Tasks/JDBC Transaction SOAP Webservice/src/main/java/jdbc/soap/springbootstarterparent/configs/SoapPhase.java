package jdbc.soap.springbootstarterparent.configs;

import jdbc.soap.springbootstarterparent.dao.Transaction;
import jdbc.soap.springbootstarterparent.dao.TransactionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import services.transaction.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Endpoint
public class SoapPhase {
    private final String url = "http://transaction.services";
    @Autowired
    private TransactionService transactionService;

   // @PreAuthorize("hasAnyAuthority('customer')")
    @PayloadRoot(namespace = url, localPart = "filterBySenderRequest")
    @ResponsePayload
    public FilterBySenderResponse filterSender(@RequestPayload FilterBySenderRequest filterBySenderRequest) {
        FilterBySenderResponse filterBySenderResponse = new FilterBySenderResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        List<services.transaction.Transaction> returnTransaction = new ArrayList<>();

        List<Transaction> received = transactionService.findBySender(filterBySenderRequest.getSender());

        Iterator<Transaction> iterator = received.iterator();

        while (iterator.hasNext()) {
            services.transaction.Transaction currentTransaction = new services.transaction.Transaction();
            BeanUtils.copyProperties(iterator.next(), currentTransaction);
            returnTransaction.add(currentTransaction);
        }

        serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage("Transaction were fetched");

        filterBySenderResponse.setServiceStatus(serviceStatus);
        filterBySenderResponse.getTransaction().addAll(returnTransaction);

        return filterBySenderResponse;
    }

   // @PreAuthorize("hasAnyAuthority('customer')")
    @PayloadRoot(namespace = url, localPart = "filterByReceiverRequest")
    @ResponsePayload
    public FilterByReceiverResponse filterReceiver(@RequestPayload FilterByReceiverRequest filterByReceiverRequest) {
        FilterByReceiverResponse filterByReceiverResponse = new FilterByReceiverResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        List<services.transaction.Transaction> returnTransaction = new ArrayList<>();

        List<Transaction> received = transactionService.findByReceiver(filterByReceiverRequest.getReceiver());

        Iterator<Transaction> iterator = received.iterator();

        while (iterator.hasNext()) {
            services.transaction.Transaction currentTransaction = new services.transaction.Transaction();
            BeanUtils.copyProperties(iterator.next(), currentTransaction);
            returnTransaction.add(currentTransaction);
        }

        serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage("Receiver were fetched");

        filterByReceiverResponse.setServiceStatus(serviceStatus);
        filterByReceiverResponse.getTransaction().addAll(returnTransaction);

        return filterByReceiverResponse;
    }

   // @PreAuthorize("hasAnyAuthority('customer')")
    @PayloadRoot(namespace = url, localPart = "filterByAmountRequest")
    @ResponsePayload
    public FilterByAmountResponse filterAmount(@RequestPayload FilterByAmountRequest filterByAmountRequest) {
        FilterByAmountResponse filterByAmountResponse = new FilterByAmountResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        List<services.transaction.Transaction> returnTransaction = new ArrayList<>();

        List<Transaction> received = transactionService.findByAmount(filterByAmountRequest.getAmount());

        Iterator<Transaction> iterator = received.iterator();

        while (iterator.hasNext()) {
            services.transaction.Transaction currentTransaction = new services.transaction.Transaction();
            BeanUtils.copyProperties(iterator.next(), currentTransaction);
            returnTransaction.add(currentTransaction);
        }

        serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage("Receiver were fetched");

        filterByAmountResponse.setServiceStatus(serviceStatus);
        filterByAmountResponse.getTransaction().addAll(returnTransaction);

        return filterByAmountResponse;
    }

   // @PreAuthorize("hasAnyAuthority('admin')")
    @PayloadRoot(namespace = url, localPart = "newTransactionRequest")
    @ResponsePayload
    public NewTransactionResponse addNewTransaction(@RequestPayload NewTransactionRequest newTransactionRequest) {
        NewTransactionResponse newTransactionResponse = new NewTransactionResponse();
        ServiceStatus serviceStatus = new ServiceStatus();

        services.transaction.Transaction actual = newTransactionRequest.getTransaction();
        Date date=newTransactionRequest.getTransaction().getTransactionDate().toGregorianCalendar().getTime();
        System.out.println(actual.getTransactionId()+" "+actual.getTransactionDate());
        Transaction daoTransaction = new Transaction();
        daoTransaction.setTransactionDate(date);
        BeanUtils.copyProperties(actual, daoTransaction);
        System.out.println(daoTransaction);
        daoTransaction = transactionService.apiSave(daoTransaction);

        if (daoTransaction != null) {
            serviceStatus.setStatus("SUCCESS");
            BeanUtils.copyProperties(daoTransaction, actual);
            newTransactionResponse.setTransaction(actual);
            serviceStatus.setMessage(actual.getTransactionId() + " has inserted");
        } else {
            serviceStatus.setStatus("FAILURE");
            serviceStatus.setMessage(actual.getTransactionId() + " hasn't inserted");
        }
        newTransactionResponse.setServiceStatus(serviceStatus);
        return newTransactionResponse;
    }

   // @PreAuthorize("hasAnyAuthority('manager','admin')")
    @PayloadRoot(namespace = url, localPart = "updateTransactionRequest")
    @ResponsePayload
    public UpdateTransactionResponse updatingTransaction(@RequestPayload UpdateTransactionRequest updateTransactionRequest){
        UpdateTransactionResponse updateTransactionResponse=new UpdateTransactionResponse();
        ServiceStatus serviceStatus=new ServiceStatus();
        services.transaction.Transaction transaction=new services.transaction.Transaction();

        Transaction daoTransaction=new Transaction();
        BeanUtils.copyProperties(updateTransactionRequest.getTransaction(),daoTransaction);

        daoTransaction = transactionService.updateTransaction(daoTransaction);

        if(daoTransaction!=null){
            serviceStatus.setStatus("SUCCESS");
            serviceStatus.setMessage(daoTransaction.getTransactionRemarks()+" has updated");
        }
        else{
            serviceStatus.setStatus("FAILURE");
            serviceStatus.setMessage(daoTransaction.getTransactionRemarks()+" hasn't updated");
        }

        BeanUtils.copyProperties(daoTransaction,transaction);

        updateTransactionResponse.setServiceStatus(serviceStatus);
        updateTransactionResponse.setTransaction(transaction);

        return updateTransactionResponse;
    }

   // @PreAuthorize("hasAnyAuthority('admin')")
    @PayloadRoot(namespace = url, localPart ="closeTransactionRequest")
    @ResponsePayload
    public CloseTransactionResponse closeTransaction(@RequestPayload CloseTransactionRequest closeTransactionRequest){
        CloseTransactionResponse closeTransactionResponse=new CloseTransactionResponse();
        ServiceStatus serviceStatus=new ServiceStatus();
        services.transaction.Transaction transaction=new services.transaction.Transaction();

        String deleteTransaction = transactionService.closeTransaction(closeTransactionRequest.getStartDate(),closeTransactionRequest.getEndDate());
        if(deleteTransaction.contains("Invalid")){
            serviceStatus.setStatus("FAILURE");
        }
        else
            serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage(deleteTransaction);
        closeTransactionResponse.setServiceStatus(serviceStatus);
        return closeTransactionResponse;
    }

}



