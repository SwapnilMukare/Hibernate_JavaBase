import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table (name="Teacher")
public class Teacher 
{@Id
@GeneratedValue(generator="Tid")
@SequenceGenerator(name="Tid",sequenceName="Hibernate_Tid",initialValue=1,allocationSize=1)
int tid;
int salary;
String name;

public int getTid() {
	return tid;
}
public void setTid(int tid) {
	this.tid = tid;
}
public int getSalary() {
	return salary;
}
public void setSalary(int salary) {
	this.salary = salary;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}@Override
public String toString() {
	return "Teacher [tid=" + tid + ", salary=" + salary + ", name=" + name + "]";
}
}
