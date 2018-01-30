package examples.stateless;

import java.util.Collection;

import examples.model.Project;

public interface ProjectService {
    public Project createProject(String name);
    public Collection<Project> findAllProjects();
}
