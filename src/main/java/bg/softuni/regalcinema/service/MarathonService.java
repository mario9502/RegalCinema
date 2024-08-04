package bg.softuni.regalcinema.service;

import bg.softuni.regalcinema.model.dtos.importDtos.AddMarathonDto;

public interface MarathonService {
    boolean add(AddMarathonDto marathonDto);
}
