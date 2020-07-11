package com.projectmanagementthesis.service.project;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectmanagementthesis.model.Activity;
import com.projectmanagementthesis.model.Project;
import com.projectmanagementthesis.model.Task;
import com.projectmanagementthesis.model.UAKey;
import com.projectmanagementthesis.model.User;
import com.projectmanagementthesis.model.UserActivityHour;
import com.projectmanagementthesis.model.UserType;
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
	
	public List<User> getAvailableUsers() {
		return userRepository.findByuserRole(UserType.USER);
	}
	
	public List<Activity> getActivitiesPerProject(Integer id) {
		return activityRepository.findByProjectId(id);
	}
	
	public List<User> getUsersAssociated(Integer activityId){
		Activity activity = this.getSingleActivity(activityId);
		List<UserActivityHour> uahActivity = userActivityHourRepository.findByActivity(activity);
		List<User> usersAssociated = new LinkedList<User>();
		for(UserActivityHour uah : uahActivity) {
			usersAssociated.add(uah.getUser());
		}
		return usersAssociated;
	}
	
	public List<Activity> getActivitiesAssociated(Long userId){
		User user = this.getSingleUser(userId);
		List<UserActivityHour> uahActivity = userActivityHourRepository.findByUser(user);
		List<Activity> activitiesAssociated = new LinkedList<Activity>();
		for(UserActivityHour uah : uahActivity) {
			activitiesAssociated.add(uah.getActivity());
		}
		return activitiesAssociated;
	}
	
	public UserActivityHour getUserActivityHour(long user_id, Integer activity_id) {
		Optional<UserActivityHour> result = userActivityHourRepository.findById(new UAKey(user_id,activity_id));
		if (result.isPresent())
			return result.get();
		return null;
	}
	
	public float getCurrentProjectBudget(Integer project_id) {
		Project current = this.getSingleProject(project_id);
		List<Activity> activities = current.getActivities_list();
		float result = 0;
		for(Activity activity : activities)	result+=activity.getBudget();
		return result;
	}
	
	public boolean RegisterHours(UserActivityHour userActivityHour, int hours) {
		User currentUser = userActivityHour.getUser();
		Activity currentActivity = userActivityHour.getActivity();
		
		if(checkHours(currentUser,currentActivity,hours)) {
			updateActivityBudget(hours, currentUser.getPricePerHour(), currentActivity);
			updateUserDailyHours(hours, currentUser);
			updateUserActivityHour(hours, userActivityHour);
			return true;
		}
		return false;		
	}
	
	private void updateUserActivityHour(int hours, UserActivityHour userActivityHour) {
		int newHours = userActivityHour.getHours() + hours;
		userActivityHour.setHours(newHours);
		userActivityHourRepository.save(userActivityHour);
	}
	
	private void updateActivityBudget(int hours, float price, Activity activity) {
		float newActivityBudget = hours * price + activity.getCurrentBudget();
		activity.setCurrentBudget(newActivityBudget);
		activityRepository.save(activity);
	}
	
	private void updateUserDailyHours(int hours, User user) {
		int newUserDailyHours = user.getDailyHours() + hours;
		user.setDailyHours(newUserDailyHours);
		userRepository.save(user);
	}
	
	private boolean checkHours(User user, Activity activity, int hours) {
		if (user.getDailyHours() + hours >= 9)	return false;
		if ((user.getPricePerHour() * hours + activity.getCurrentBudget()) <= activity.getBudget())
			return true;
		return false;
	}
	
}