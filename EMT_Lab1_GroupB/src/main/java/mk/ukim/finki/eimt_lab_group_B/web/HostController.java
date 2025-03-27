package mk.ukim.finki.eimt_lab_group_B.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.eimt_lab_group_B.model.Host;
import mk.ukim.finki.eimt_lab_group_B.model.dtos.HostDto;
import mk.ukim.finki.eimt_lab_group_B.service.HostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/hosts")
@Tag(name = "Host Controller", description = "API for managing hosts")
public class HostController {
    private final HostService hostService;

    public HostController(HostService hostService) {
        this.hostService = hostService;
    }

    @GetMapping
    @Operation(summary = "Get all hosts", description = "Retrieve a list of all hosts")
    public List<Host> findAll() {
        return hostService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get host by id", description = "Retrieve a host by id")
    public ResponseEntity<Host> findById(@PathVariable Long id) {
        return this.hostService.findById(id)
                .map(h -> ResponseEntity.ok().body(h))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    @Operation(summary = "Add a new host", description = "Create and store a new host")
    public ResponseEntity<Host> save(@RequestBody HostDto host) {
        return this.hostService.save(host)
                .map(h -> ResponseEntity.ok().body(h))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/edit/{id}")
    @Operation(summary = "Edit host", description = "Edit an existing host by id")
    public ResponseEntity<Host> update(@PathVariable Long id, @RequestBody HostDto host) {
        return this.hostService.update(id, host)
                .map(h -> ResponseEntity.ok().body(h))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete host", description = "Delete a host by id")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if(hostService.findById(id).isPresent()) {
            hostService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}

