package com.javarush.task.task36.task3608.model;

import com.javarush.task.task36.task3608.bean.User;
import com.javarush.task.task36.task3608.model.service.UserService;
import com.javarush.task.task36.task3608.model.service.UserServiceImpl;

import java.util.List;

/**
 * Created by n_alex on 05.04.2017.
 */
public class MainModel implements Model
{
	private ModelData modelData = new ModelData();
	private UserService userService = new UserServiceImpl();
	
	@Override
	public ModelData getModelData()
	{
		return modelData;
	}
	
	private List<User> getAllUsers()
	{
		return userService.filterOnlyActiveUsers(userService.getUsersBetweenLevels(1, 100));
	}
	
	@Override
	public void loadUsers()
	{
		modelData.setUsers(getAllUsers());
		modelData.setDisplayDeletedUserList(false);
	}
	
	public void loadDeletedUsers()
	{
		modelData.setUsers(userService.getAllDeletedUsers());
		modelData.setDisplayDeletedUserList(true);
	}
	
	public void loadUserById(long userId)
	{
		User user = userService.getUsersById(userId);
		modelData.setActiveUser(user);
	}
	
	public void deleteUserById(long id)
	{
		userService.deleteUser(id);
		modelData.setUsers(getAllUsers());
		modelData.setDisplayDeletedUserList(false);
	}
	
	public void changeUserData(String name, long id, int level)
	{
		userService.createOrUpdateUser(name, id, level);
		modelData.setUsers(getAllUsers());
		modelData.setDisplayDeletedUserList(false);
	}
}
