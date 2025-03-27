package mk.ukim.finki.eimt_lab_group_B.service;

import mk.ukim.finki.eimt_lab_group_B.model.Host;
import mk.ukim.finki.eimt_lab_group_B.model.dtos.HostDto;

import java.util.List;
import java.util.Optional;

public interface HostService {
    List<Host> findAll();
    Optional<Host> findById(Long id);
    Optional<Host> save(HostDto host);
    Optional<Host> update(Long id, HostDto host);
    void deleteById(Long id);
}
