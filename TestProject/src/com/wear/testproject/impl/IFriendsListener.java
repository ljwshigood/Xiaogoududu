package com.wear.testproject.impl;

import com.wear.testproject.bean.FriendBeanComponment;
import com.wear.testproject.bean.FriendsComponment;
import com.wear.testproject.bean.ResultBean;

public interface IFriendsListener {
	
	public void queryFriendList(FriendBeanComponment bean);
	
	public void queryFriend(FriendsComponment bean);
	
	public void passFriendReq(ResultBean bean);

}
