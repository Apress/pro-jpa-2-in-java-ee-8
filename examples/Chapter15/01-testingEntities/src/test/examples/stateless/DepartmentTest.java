package examples.stateless;

import junit.framework.TestCase;
import examples.model.Department;

public class DepartmentTest extends TestCase {

    public void testValidDepartmentId() throws Exception {
        Department dept = new Department();
        dept.setId("NA65");
        assertEquals("NA65", dept.getId());
    }
    
    public void testDepartmentIdInvalidLength() throws Exception {
        Department dept = new Department();
        try {
            dept.setId("NA6");
            fail("Department identifiers must be four characters");
        } catch (IllegalArgumentException e) {
        }
    }
    
    public void testDepartmentIdCase() throws Exception {
        Department dept = new Department();
        dept.setId("na65");
        assertEquals("NA65", dept.getId());
    }
}
