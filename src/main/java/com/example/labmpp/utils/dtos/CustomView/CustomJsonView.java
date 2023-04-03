package com.example.labmpp.utils.dtos.CustomView;

public class CustomJsonView {
    public interface id {}
    public interface CoreData extends id {}

    public interface FullDataCar extends CoreData {}
    public interface FullDataServiceHistory extends CoreData {}
    public interface FullDataService extends CoreData {}
    public interface FullDataCustomer extends CoreData {}
    public interface FullDataRentalTransaction extends CoreData {}
}
