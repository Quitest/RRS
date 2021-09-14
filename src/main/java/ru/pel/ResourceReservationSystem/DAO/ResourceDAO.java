package ru.pel.ResourceReservationSystem.DAO;

import org.springframework.stereotype.Component;
import ru.pel.ResourceReservationSystem.models.Resource;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class ResourceDAO {
    private static int ID = 0;
    private List<Resource> resourceList;

    {
        resourceList = new ArrayList<>();
        resourceList.add(new Resource(++ID, LocalDateTime.now(), "Resource title #" + ID));
        resourceList.add(new Resource(++ID, LocalDateTime.now(), "Resource title #" + ID));
        resourceList.add(new Resource(++ID, LocalDateTime.now(), "Resource title #" + ID));
        resourceList.add(new Resource(++ID, LocalDateTime.now(), "Resource title #" + ID));
        resourceList.add(new Resource(++ID, LocalDateTime.now(), "Resource title #" + ID));
    }

    public List<Resource> getAllResources() {
        return resourceList;
    }

    public Resource getResourceById(int id) {
        return resourceList.stream()
                .filter(res -> id == res.getId())
                .findFirst().orElse(null);
    }
}
