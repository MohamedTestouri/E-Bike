package tn.esprit.pidev.services;

import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;
import tn.esprit.pidev.entities.User;
import tn.esprit.pidev.utils.Database;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class UserService {
    public ArrayList<User> userArrayList;

    public static UserService instance=null;
    public boolean resultOK;
    private ConnectionRequest connectionRequest;

    public UserService() {
        connectionRequest = new ConnectionRequest();
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }


    public boolean login(User user) {
        String url = Database.BASE_URL + "user/api/login?email=" + user.getEmail() + "&password=" + user.getPassword();
        //String url = Database.BASE_URL + "user/api/login?email=azerazer@azerazer.az&password=azerazerazerazer";
        System.out.println(url);
        System.out.println("i am here");
        connectionRequest.setUrl(url);
        connectionRequest.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                userArrayList = parseUser(new String(connectionRequest.getResponseData()));
                connectionRequest.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(connectionRequest);
        if (userArrayList.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public User findUser(String email) {
        User user = new User();
        String url = Database.BASE_URL + "user/api/findemail?email=" + email; // Add Symfony URL Here
        connectionRequest.setUrl(url);
        connectionRequest.setPost(false);
        connectionRequest.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                userArrayList = parseUser(new String(connectionRequest.getResponseData()));
                user.setId(userArrayList.get(0).getId());
                user.setEmail(userArrayList.get(0).getEmail());
                user.setPassword(userArrayList.get(0).getPassword());
                user.setName(userArrayList.get(0).getName());
                user.setPhone(userArrayList.get(0).getPhone());
                user.setType(userArrayList.get(0).getType());
                connectionRequest.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(connectionRequest);
        return user;
    }

    public boolean register(User user) {
        String url = Database.BASE_URL + "user/api/signup?email=" + user.getEmail() +
                "&password=" + user.getPassword() +
                "&name=" + user.getName() +
                "&type="+user.getType()+
                "&phone=" + user.getPhone(); // Add Symfony URL here
        connectionRequest.setUrl(url);
        connectionRequest.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = connectionRequest.getResponseCode() == 200; //Code HTTP 200 OK
                connectionRequest.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(connectionRequest);
        return resultOK;

    }

    public ArrayList<User> parseUser(String jsonText) {
        try {
            userArrayList = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> userListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            // List<Map<String, Object>> list = (List<Map<String, Object>>) userListJson.get("root");
            // for (Map<String, Object> obj : list) {
            User user = new User();
            user.setId((int) Float.parseFloat(userListJson.get("id").toString()));
            user.setName(userListJson.get("name").toString());
            user.setPhone((int) Float.parseFloat(userListJson.get("phone").toString()));
            user.setEmail(userListJson.get("email").toString());
            user.setType(userListJson.get("type").toString());
            userArrayList.add(user);
            // }
        } catch (IOException ex) {
        }
        return userArrayList;
    }

}
