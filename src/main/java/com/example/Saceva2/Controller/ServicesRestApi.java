package com.example.Saceva2.Controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Saceva2.Bo.Account;
import com.example.Saceva2.Bo.Role;
import com.example.Saceva2.Bo.TabConfig;
import com.example.Saceva2.Bo.User;
import com.example.Saceva2.Db.ReadFromDb;
import com.example.Saceva2.Repository.IRepoAccount;
import com.example.Saceva2.Repository.IRepoRole;
import com.example.Saceva2.Repository.IRepoTabConfig;
import com.example.Saceva2.Repository.IRepoUser;
import com.example.Saceva2.SpringScheduler.Tool.Tool;
import com.example.Saceva2.SpringScheduler.Tool.ListDipendentToUser;

import jakarta.servlet.http.HttpSession;

@EnableAsync
@RestController
@RequestMapping(value = "/ContorllerSaceva2")
public class ServicesRestApi {// test

	@Autowired
	IRepoUser iUser;
	@Autowired
	IRepoAccount iAccount;
	@Autowired
	IRepoRole iRole;
	// test
	@Autowired
	IRepoTabConfig iTabConfig;

	// var
	private SimpleDateFormat dateFormat = null;
	private ReadFromDb readFromDb = new ReadFromDb();
	private Tool tool = new Tool();
	private HashMap<String, String> dataFromTabConfig = null;
	private ArrayList<User> listOfUser = null;

	private String dateScheduling = "";

	ListDipendentToUser updateDipendnet = new ListDipendentToUser();

	//////////////////////////// Login\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	@PostMapping(value = "login")
	public boolean login(Account a) {
		boolean access = false;
		System.out.println("pass lenght" + a.getPass().length());
		String esito = "";
		System.out.println(
				"From html username : " + a.getuName() + ", Email : " + a.getEmail() + "pass : " + a.getPass());
		BCryptPasswordEncoder cryptoPs = new BCryptPasswordEncoder();

		List<Account> accounts = iAccount.findAll();
		if (null == a.getEmail())
			System.out.println();
		if ("".equals(a.getuName()) || null == a.getuName())
			a.setuName("");
		if ("".equals(a.getEmail()) || null == a.getEmail())
			a.setEmail("");
		if (("".equals(a.getuName()) || null == a.getuName()) && ("".equals(a.getEmail()) || null == a.getEmail()))
			esito = "missing UserName , try again";
		else {
			for (int i = 0; i < accounts.size(); i++) {
				if ((a.getuName().equals(accounts.get(i).getuName()) || a.getEmail().equals(accounts.get(i).getEmail()))
						&& cryptoPs.matches(a.getPass(), accounts.get(i).getPass())) { // if username or email and
																						// password exist then login

					System.out.println(" pass : " + accounts.get(i).getPass());
					System.out.println("account found on db");
					System.out.println("From html username : " + a.getuName() + ", Email : " + a.getEmail() + "pass : "
							+ a.getPass());
					System.out.println("Found on db username : " + accounts.get(i).getuName());
					esito = "Approved access";
					access = true;
					break;
				}
//				if((a.getuName().equals(accounts.get(i).getuName()) || a.getEmail().equals(accounts.get(i).getEmail())) 
//						&& a.getPass().equals(accounts.get(i).getPass())) {
//					System.out.println("account found on db");
//					System.out.println("From html username : " + a.getuName() + ", Email : "+ a.getEmail() + "pass : " + a.getPass());
//					System.out.println("Found on db username : " + accounts.get(i).getuName());
//					esito = "Accesso eseguito";
//					break;
//				}
				if (i == accounts.size() - 1) {
					System.err.println("match not found");
					esito = "Access denied";
					access = false;
				}
			}
		}
		System.out.println(esito);
		return access;
	}

	//////////////////////////// User\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	@RequestMapping(value = "/insertUser", method = RequestMethod.POST)
	public List<User> register(User u, Account a, int idRole) throws InterruptedException {// insert and update
		System.out.println("method of register");
		String passCrypt = "";

		Role r = iRole.findById(idRole);// work
//		if (a != null) {
			BCryptPasswordEncoder cryptoPs = new BCryptPasswordEncoder();
			passCrypt = cryptoPs.encode(a.getPass());
			a.setPass(passCrypt);
//		List<Permesso> permessi = ip.findAll(); for not forget it
//		for (int i = 0; i < permessi.size(); i++) {
//			System.out.println("serach ruolo");
//			if (permessi.get(i).getId() == idPermesso) {
//				System.out.println("ruolo found");
//				p = permessi.get(i);
//				break;
//			}
//			if (i == permessi.size() - 1) {
//				System.err.println("ruolo not found, sorry id not exsist");
//			}
//		}

		a.setRole(r);
		u.setAccount(a);
		System.out.println(" user : " + u.getName() + " role :  " + idRole);
		iUser.save(u);
//		return true;
		return iUser.findAll();

	}

	@RequestMapping(value = "/deleteUser", method = RequestMethod.DELETE)
	public String delete(@RequestParam(defaultValue = "0") int idUser, int[] listToDelete) {// work delete account and
																							// utente
		System.out.println("method of delete");
		String msg = "";
		ArrayList<User> allUser = (ArrayList<User>) iUser.findAll();
		HashMap<Integer, User> userCheckedToDelete = new HashMap<Integer, User>();
		try {
			if (idUser == 0 && (listToDelete.length == 0 || listToDelete == null)) {
				msg = "sorry but idUser or listToDelete are missing";
			} else {
				// check if listToDelete id exist on db
				if (listToDelete != null && listToDelete.length != 0) {
					for (int i = 0; i < listToDelete.length; i++) {
						User u = iUser.findById(listToDelete[i]);
						if (u != null)
							userCheckedToDelete.put(u.getIdUser(), u);
					}
				}
				System.out.println("idUser : " + idUser);
				System.out.println("List idUser checked to delete : " + userCheckedToDelete.toString());
				if (userCheckedToDelete.size() != 0 && idUser == 0) {
					for (int toDelete : userCheckedToDelete.keySet()) {
						msg = "Delte user with id : " + toDelete;
						iUser.deleteById(toDelete);
					}
				} else if (idUser != 0) {
					if (iUser.findById(idUser) != null) {
						System.out.println("delete one user");
						msg = "Delete one user with id : " + idUser;
						iUser.deleteById(idUser);
					}
				}
			}
		} catch (NullPointerException e) {
			System.err.println("the list is empty");
		}

		return msg;
	}

//	@RequestMapping(value = "tabellaUteni", method = RequestMethod.GET)
//	public String tabellaUtenti(HttpSession session) {
//		session.setAttribute("TabUtenti", iu.findAll());
//		return "tabellaUtenti";
//	}

	@RequestMapping(value = "tabUsers", method = RequestMethod.GET)
	public List<User> tabellaUtenti() {
		return iUser.findAll();
	}

	@RequestMapping(value = "tabAccount", method = RequestMethod.GET)
	public List<Account> tabellaAccount() {
		return iAccount.findAll();
	}

	@RequestMapping(value = "tabUser", method = RequestMethod.GET)
	public String tabellaUtente(@RequestParam int idUtente, HttpSession session) {
		session.setAttribute("TabUtente", iUser.findById(idUtente));
		return "tabellaUtente";
	}

	////////////////////// Role\\\\\\\\\\\\\\\\\\\\ da provare solo la ricerca
	@PostMapping(value = "insertRole")
	public List<Role> insertRuolo(Role r) {
		System.out.println("permesso : " + r.toString());
		iRole.save(r);// work
		return iRole.findAll();
	}

//	@RequestMapping(value = "updateRuolo", method = RequestMethod.POST)
//	public List<Permesso> modRuolo(Permesso p) {
//		ip.save(p);// work
//		return ip.findAll();
//	}

	@DeleteMapping(value = "deleteRole")
	public List<Role> deleteRuolo(Role r) {
		iRole.delete(r);// work
		return iRole.findAll();
	}

	@GetMapping(value = "readRole")
	public List<Role> readRuolo(@RequestParam boolean all, String role) {
		if (all) {
			return iRole.findAll();// work
		} else {
			Role r = iRole.findByRole(role);
			List<Role> listOneRuolo = new ArrayList<Role>();
			listOneRuolo.add(r);
			return listOneRuolo; // work
		}
	}
	// The user account role has been completed and works correctly, by reverifying
	// the login

	// test for scheduling read from db
	///////////////////////// tabConfig\\\\\\\\\\\\\\\\\\\\\\\
	@PostMapping(value = "insertConfig")
	public List<TabConfig> insertConfig(TabConfig tabConfig) {
		System.out.println("TabConfig : " + tabConfig.toString());
		iTabConfig.save(tabConfig);
		return iTabConfig.findAll();
	}

	@DeleteMapping(value = "deleteConfig")
	public List<TabConfig> deleteTabConfig(TabConfig tabConfig) {
		iTabConfig.delete(tabConfig);
		return iTabConfig.findAll();
	}

	@GetMapping(value = "readTabConfig")
	public List<TabConfig> readTabConfig(@RequestParam boolean all, String keyTabConfig) {
		if (all) {
			return iTabConfig.findAll();
		} else {
			TabConfig config = iTabConfig.findByKey(keyTabConfig);
			List<TabConfig> listOneConfig = new ArrayList<TabConfig>();
			listOneConfig.add(config);
			return listOneConfig;
		}
	}
	// end test for scheduling read from db

//	@Override
//	public void onApplicationEvent(ApplicationReadyEvent event) {
//		try {
//			dataFromTabConfig = readFromDb.readFromConfig(ConnectionDb.getInstance().getConnection());
//			dateScheduling = tool.extractDataFromHashMap(dataFromTabConfig, "DATE_SCHEDULING");
//			
//			ArrayList<User> listOfUser = updateDipendnet.bodyRun(dataFromTabConfig, readFromDb, dateScheduling);
//			if(listOfUser != null && listOfUser.size() != 0) {
//				for(User user : listOfUser) {
////					Role r = iRole.findByRole(user.getAccount().getRole().getRuolo());//error
////					Account a = iAccount.findByEmail(user.getAccount().getEmail());
//					register(user, null, 1);
//					System.out.println("Try to insert user : " + user.getName());
//					Thread.sleep(500);
//				}
//			}else {
//				System.err.println("i am sorry but the list \"listOfUser\" are empity , try again" );
//			}
//			
//		} catch (StreamReadException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (DatabindException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

////			Now create the time and schedule it
//		    Timer timer = new Timer();
//
////		    Use this if you want to execute it once
//		    timer.schedule(new ServicesRestApi(), new Date(), 20000);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//	}

//	@Override
//	public void run() {
//		try {
//			ArrayList<User> listOfUser = updateDipendnet.listOfUsers(dataFromTabConfig, readFromDb);
//			if(listOfUser != null && listOfUser.size() != 0) {
//				for(User user : listOfUser) {
////					Role r = iRole.findByRole(user.getAccount().getRole().getRuolo());//error
////					Account a = iAccount.findByEmail(user.getAccount().getEmail());
//					register(user, null, 1);
//					System.out.println("Try to insert user : " + user.getName());
//				}
//			}else {
//				System.err.println("i am sorry but the list \"listOfUser\" are empity , try again" );
//			}
//			
//		} catch (StreamReadException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (DatabindException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}

}
