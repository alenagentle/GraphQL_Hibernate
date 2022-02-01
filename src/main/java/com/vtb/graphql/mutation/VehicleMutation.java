package com.vtb.graphql.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.vtb.graphql.dao.entity.Vehicle;
import com.vtb.graphql.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VehicleMutation implements GraphQLMutationResolver {

    @Autowired
    private VehicleService vehicleService;

    public Vehicle createVehicle(final String type,
                                 final String modelCode,
                                 final String brandName,
                                 final String launchDate) {
        return this.vehicleService.createVehicle(type, modelCode, brandName, launchDate);
    }

    public Vehicle updateVehicle(final int id,
                                 final String type,
                                 final String modelCode,
                                 final String brandName,
                                 final String launchDate) throws Exception {
        return this.vehicleService.updateVehicle(id, type, modelCode, brandName, launchDate);
    }

    public Vehicle deleteVehicle(final int id) throws Exception {
        return this.vehicleService.deleteVehicle(id);
    }
}
