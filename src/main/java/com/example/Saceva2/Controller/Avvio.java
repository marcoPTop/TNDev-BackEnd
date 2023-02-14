package com.example.Saceva2.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Saceva2.Bo.Account;
import com.example.Saceva2.Bo.Permesso;
import com.example.Saceva2.Bo.Utente;
import com.example.Saceva2.Repository.InterfaceAccountJpa;
import com.example.Saceva2.Repository.InterfacePermessoJps;
import com.example.Saceva2.Repository.InterfaceUtenteJpa;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/ContorllerSaceva2")
public class Avvio {// prova

	@Autowired
	InterfaceUtenteJpa iu;
	@Autowired
	InterfaceAccountJpa ia;
	@Autowired
	InterfacePermessoJps ip;

	//////////////////////////// Utente\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	@RequestMapping(value = "/insertUtente", method = RequestMethod.POST)
	public List<Utente> register(Utente u, Account a, int idPermesso) throws InterruptedException {// insert and update
		System.out.println("method of register");
//		System.out.println("utente : " + u.toString());
		Permesso p = null;
		List<Permesso> permessi = ip.findAll();
		for (int i = 0; i < permessi.size(); i++) {
			System.out.println("serach ruolo");
			if (permessi.get(i).getId() == idPermesso) {
				System.out.println("ruolo found");
				p = permessi.get(i);
				break;
			}
			if (i == permessi.size() - 1) {
				System.err.println("ruolo not found, sorry id not exsist");
			}
		}
		System.out.println("permesso p : " + p.toString());
		a.setRuolo(p);
		u.setAccount(a);
		System.out.println("utente : " + u.toString());
		iu.save(u);
//		Thread.sleep(375);
//		return ru.findAll(); java.lang.IllegalStateException: Cannot call sendError() after the response has been committed
		return iu.findAll();

	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public List<Utente> delete(@RequestParam int idUtente) {// work delete account and utente
		boolean flag = false;
		System.out.println("method of delete");
//		Utente u = ru.findById(idUtente);
//		System.out.println("utente : " + u.toString());
//		u.setAccount(a);
//		a.setRuolo(p);
		iu.deleteById(idUtente);
		return iu.findAll();
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
	////////////////////// specifica del ruolo
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
