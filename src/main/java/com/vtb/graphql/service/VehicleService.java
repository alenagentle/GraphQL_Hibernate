package com.vtb.graphql.service;

import com.vtb.graphql.dao.entity.Vehicle;
import com.vtb.graphql.dao.repository.VehicleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleService(final VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Transactional
    public Vehicle createVehicle(final String type,
                                 final String modelCode,
                                 final String brandName,
                                 final String launchDate) {
        final Vehicle vehicle = new Vehicle();
        vehicle.setType(type);
        vehicle.setModelCode(modelCode);
        vehicle.setBrandName(brandName);
        vehicle.setLaunchDate(LocalDate.parse(launchDate));
        return this.vehicleRepository.save(vehicle);
    }

    @Transactional(readOnly = true)
    public List<Vehicle> getAllVehicles(final int count) {
        return this.vehicleRepository.findAll()
                .stream()
                .limit(count)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<Vehicle> getVehicle(final int id) {
        return this.vehicleRepository.findById(id);
    }

    @Transactional
    public Vehicle updateVehicle(final int id,
                                 final String type,
                                 final String modelCode,
                                 final String brandName,
                                 final String launchDate) throws Exception {
        Vehicle vehicle = this.vehicleRepository.findById(id)
                .orElseThrow(() -> new Exception("Not found vehicle"));
        vehicle.setBrandName(brandName);
        vehicle.setType(type);
        vehicle.setLaunchDate(LocalDate.parse(launchDate));
        vehicle.setModelCode(modelCode);
        return this.vehicleRepository.save(vehicle);
    }

    @Transactional
    public Vehicle deleteVehicle(final int id) throws Exception {
        Vehicle vehicle = this.vehicleRepository.findById(id)
                .orElseThrow(() -> new Exception("Not found vehicle"));
        this.vehicleRepository.delete(vehicle);
        return vehicle;
    }
}
