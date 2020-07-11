package com.projectmanagementthesis.service.addProject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.projectmanagementthesis.model.Activity;
import com.projectmanagementthesis.model.Project;
import com.projectmanagementthesis.model.Task;
import com.projectmanagementthesis.model.UAKey;
import com.projectmanagementthesis.model.User;
import com.projectmanagementthesis.model.UserActivityHour;
import com.projectmanagementthesis.repositories.*;

@Service
public class ProjectService {
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private ActivityRepository activityRepository;
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserActivityHourRepository userActivityHourRepository;
	
	public Project addNewProject(Project project) {
		Project added = projectRepository.save(project);
		return added;
	}
	
	public User addNewUser(User user) {
		User added = userRepository.save(user);
		return added;
	}
	
	public Activity addNewActivity(Activity activity) {
		Activity added = activityRepository.save(activity);
		return added;
	}
	
	public Task addNewTask(Task task) {
		Task added = taskRepository.save(task);
		return added;
	}
	
	public UserActivityHour addUserActivityHour(UserActivityHour userActivityHour) {		
		if (userActivityHourRepository.existsById(userActivityHour.getKey()))
			return null;
		else {
			UserActivityHour added = userActivityHourRepository.save(userActivityHour);
			return added;
		}
	}
	
	public Project getSingleProject(Integer id) {
		Optional<Project> result = projectRepository.findById(id);
		if (result.isPresent())
			return result.get();
		return null;
	}
	
	public Activity getSingleActivity(Integer id) {
		Optional<Activity> result = activityRepository.findById(id);
		if (result.isPresent())
			return result.get();
		return null;
	}
	
	public User getSingleUser(long id) {
		Optional<User> result = userRepository.findById(id);
		if (result.isPresent())
			return result.get();
		return null;
	}
	
	public List<Project> getProjects() {
		List<Project> result = new ArrayList<Project>();
		projectRepository.findAll().forEach(result::add);
		
		return result;
	}
	
	public List<User> getUsers() {
		List<User> result = new ArrayList<User>();
		userRepository.findAll().forEach(result::add);
		
		return result;
	}
	
	public List<Activity> getActivitiesPerProject(Integer id) {
		return activityRepository.findByProjectId(id);
	}
	
	public List<User> getUsersAssociated(Integer activityId){
		Activity activity = getSingleActivity(activityId);
		System.out.println(activity);
		List<UserActivityHour> uahActivity = userActivityHourRepository.findByActivity(activity);
		List<User> usersAssociated = new LinkedList<User>();
		for(UserActivityHour uah : uahActivity) {
			usersAssociated.add(uah.getUser());
		}
		return usersAssociated;
	}
	
	
	public void populate() {
		Project project_1 = new Project("Project_1", 10000, LocalDate.now(), LocalDate.now().plusYears(2));
		Project project_2 = new Project("Project_2", 20000, LocalDate.now(), LocalDate.now().plusYears(2));
		
		projectRepository.save(project_1);
		projectRepository.save(project_2);
		
		Activity activity_1 = new Activity("Activity_1", 5000, project_1, LocalDate.now(), LocalDate.now().plusYears(1));
		Activity activity_2 = new Activity("Activity_2", 5000, project_1, LocalDate.now().plusYears(1), LocalDate.now().plusYears(2));
		Activity activity_3 = new Activity("Activity_3", 10000, project_2, LocalDate.now(), LocalDate.now().plusYears(1));
		Activity activity_4 = new Activity("Activity_4", 10000, project_2, LocalDate.now().plusYears(1), LocalDate.now().plusYears(2));

		activityRepository.save(activity_1);
		activityRepository.save(activity_2);
		activityRepository.save(activity_3);
		activityRepository.save(activity_4);
		
		taskRepository.save(new Task("taskName_1", activity_1));
		taskRepository.save(new Task("taskName_2", activity_1));
		taskRepository.save(new Task("taskName_3", activity_2));
		taskRepository.save(new Task("taskName_4", activity_2));
		taskRepository.save(new Task("taskName_5", activity_3));
		taskRepository.save(new Task("taskName_6", activity_3));
		taskRepository.save(new Task("taskName_7", activity_4));
		taskRepository.save(new Task("taskName_8", activity_4));
		
//		User user_1 = new User("Name_1", "Surname_1", "email_1@test.test", "password_1");
//		User user_2 = new User("Name_2", "Surname_2", "email_2@test.test", "password_2");
//		User user_3 = new User("Name_3", "Surname_3", "email_3@test.test", "password_3");
//		User user_4 = new User("Name_4", "Surname_4", "email_4@test.test", "password_4");
//		User user_5 = new User("Name_5", "Surname_5", "email_5@test.test", "password_5");
//		User user_6 = new User("Name_6", "Surname_6", "email_6@test.test", "password_6");
//		User user_7 = new User("Name_7", "Surname_7", "email_7@test.test", "password_7");
//		User user_8 = new User("Name_8", "Surname_8", "email_8@test.test", "password_8");
		
//		userRepository.save(user_1);
//		userRepository.save(user_2);
//		userRepository.save(user_3);
//		userRepository.save(user_4);
//		userRepository.save(user_5);
//		userRepository.save(user_6);
//		userRepository.save(user_7);
//		userRepository.save(user_8);
//		
////		user_1.getActivities().addAll(Arrays.asList(activity_1,activity_2));
//		userRepository.save(user_1);
		
	}
	
}