package com.example.Saceva2.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Saceva2.Bo.Account;
import com.example.Saceva2.Bo.Permesso;
import com.example.Saceva2.Bo.Utente;
import com.example.Saceva2.Repository.IRepoAccount;
import com.example.Saceva2.Repository.IRepoPermesso;
import com.example.Saceva2.Repository.IRepoUtente;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/ContorllerSaceva2")
public class Avvio {// prova

	@Autowired
	IRepoUtente iu;
	@Autowired
	IRepoAccount ia;
	@Autowired
	IRepoPermesso ip;

	//////////////////////////// Login\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	@PostMapping(value = "login")
	public String login(Account a) {

		System.out.println("pass lenght" + a.getPass().length());
		String esito = "";
		System.out.println(
				"From html username : " + a.getuName() + ", Email : " + a.getEmail() + "pass : " + a.getPass());
		BCryptPasswordEncoder cryptoPs = new BCryptPasswordEncoder();

		List<Account> accounts = ia.findAll();
		if (null == a.getEmail())
			System.out.println();
		if ("".equals(a.getuName()) || null == a.getuName())
			a.setuName("");
		if ("".equals(a.getEmail()) || null == a.getEmail())
			a.setEmail("");
		if (("".equals(a.getuName()) || null == a.getuName()) && ("".equals(a.getEmail()) || null == a.getEmail()))
			esito = "missing UserName and Password , try again";
		else {
			for (int i = 0; i < accounts.size(); i++) {
				if (cryptoPs.matches(a.getPass(), accounts.get(i).getPass())) {
					System.out.println(" pass : " + accounts.get(i).getPass());
					System.out.println("account found on db");
					System.out.println("From html username : " + a.getuName() + ", Email : " + a.getEmail() + "pass : "
							+ a.getPass());
					System.out.println("Found on db username : " + accounts.get(i).getuName());
					esito = "Accesso eseguito";
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
					System.err.println("corrispondenza non trovata");
					esito = "Accesso negato";
				}
			}
		}
		return esito;
	}

	//////////////////////////// Utente\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	@RequestMapping(value = "/insertUtente", method = RequestMethod.POST)
	public List<Utente> register(Utente u, Account a, int idPermesso) throws InterruptedException {// insert and update
		System.out.println("method of register");
		String passCrypt = "";
		Permesso p = ip.findById(idPermesso);// work
		BCryptPasswordEncoder cryptoPs = new BCryptPasswordEncoder();
		passCrypt = cryptoPs.encode(a.getPass());
		a.setPass(passCrypt);
//		List<Permesso> permessi = ip.findAll();
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
		a.setRuolo(p);
		u.setAccount(a);
		iu.save(u);

		return iu.findAll();

	}

	@RequestMapping(value = "/deleteUtente", method = RequestMethod.DELETE)
	public String delete(@RequestParam(defaultValue = "0") int idUtente, int[] listToDelete) {// work delete account and
																								// utente
		System.out.println("method of delete");
		String msg = "";
		ArrayList<Utente> allUtenti = (ArrayList<Utente>) iu.findAll();
		HashMap<Integer, Utente> utentiCheckToDelete = new HashMap<Integer, Utente>();
		try {
			if (idUtente == 0 && (listToDelete.length == 0 || listToDelete == null)) {
				msg = "sorry but idUtente or listToDelete are missing";
			} else {
				// check if listToDelete id exist on db
				for (int i = 0; i < listToDelete.length; i++) {
					Utente u = iu.findById(listToDelete[i]);
					if (u != null)
						utentiCheckToDelete.put(u.getIdUtente(), u);
				}
				System.out.println("idUtente : " + idUtente);
				System.out.println("List idUtenti verificati da eliminare : " + utentiCheckToDelete.toString());
				if (utentiCheckToDelete.size() != 0 && idUtente == 0) {
					for (int toDelete : utentiCheckToDelete.keySet()) {
						msg = "Delte utente with id : " + toDelete;
						iu.deleteById(toDelete);
					}
				} else if (idUtente != 0) {
					if (iu.findById(idUtente) != null) {
						msg = "Delete one Utente with id : " + idUtente;
						iu.deleteById(idUtente);
					}
				}
			}
		} catch (NullPointerException e) {
			System.err.println("Lista vuota");
		}

		return msg;
	}

//	@RequestMapping(value = "tabellaUteni", method = RequestMethod.GET)
//	public String tabellaUtenti(HttpSession session) {
//		session.setAttribute("TabUtenti", iu.findAll());
//		return "tabellaUtenti";
//	}

	@RequestMapping(value = "tabellaUteni", method = RequestMethod.GET)
	public List<Utente> tabellaUtenti() {
		return iu.findAll();
	}

	@RequestMapping(value = "tabellaAccount", method = RequestMethod.GET)
	public List<Account> tabellaAccount() {
		return ia.findAll();
	}

	@RequestMapping(value = "tabellaUtente", method = RequestMethod.GET)
	public String tabellaUtente(@RequestParam int idUtente, HttpSession session) {
		session.setAttribute("TabUtente", iu.findById(idUtente));
		return "tabellaUtente";
	}

	////////////////////// permesso\\\\\\\\\\\\\\\\\\\\ da provare solo la ricerca
	@PostMapping(value = "insertRuolo")
	public List<Permesso> insertRuolo(Permesso p) {
		System.out.println("permesso : " + p.toString());
		ip.save(p);// work
		return ip.findAll();
	}

//	@RequestMapping(value = "updateRuolo", method = RequestMethod.POST)
//	public List<Permesso> modRuolo(Permesso p) {
//		ip.save(p);// work
//		return ip.findAll();
//	}

	@DeleteMapping(value = "deleteRuolo")
	public List<Permesso> deleteRuolo(Permesso p) {
		ip.delete(p);// work
		return ip.findAll();
	}

	@GetMapping(value = "readRuoli")
	public List<Permesso> readRuolo(@RequestParam boolean all, String ruolo) {
		if (all) {
			return ip.findAll();// work
		} else {
			Permesso p = new Permesso();
			p.setRuolo(ruolo);
			List<Permesso> listOneRuolo = new ArrayList<Permesso>();
			listOneRuolo.add(p);
			return listOneRuolo; // work
		}
	}

}
