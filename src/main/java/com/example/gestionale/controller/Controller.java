package com.example.gestionale.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.stereotype.*;

import com.example.gestionale.Bo.Account;
import com.example.gestionale.Bo.Permesso;
import com.example.gestionale.Bo.Utente;
import com.example.gestionale.Repository.IRepoAccount;
import com.example.gestionale.Repository.IRepoPermesso;
import com.example.gestionale.Repository.IRepoUtente;

@RestController
public class Controller {

	@Autowired
	IRepoAccount ra;
	@Autowired
	IRepoPermesso rp;
	@Autowired
	IRepoUtente ru;

//	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public boolean login(Account a) {
		boolean flag = false;
		System.out.println("Account : " + a.toString());
//		Account a = new Account();
//		a.setPass(password);
//		a.setUserName(username);
		List<Account> listAccount = ra.findAll();
		System.out.println("length array : " + listAccount.size());
		if (!listAccount.isEmpty()) {
			System.out.println("array : " + listAccount.toString());
			if (!a.getUserName().isEmpty() && !a.getPassword().isEmpty()) {
				System.out.println("obj exist from html");
				for (int i = 0; i < listAccount.size(); i++) {
					System.out.println("start for");
					Account b = ra.findById(listAccount.get(i).getIdAccount());
					System.out.println("account in array : " + b.toString());
					if (b.getUserName().equalsIgnoreCase(a.getUserName())
							&& b.getPassword().equalsIgnoreCase(a.getPassword())) {
						flag = true;

					} else {
						System.err.println("error not found account");
						flag = false;
					}
				}
			}
		} else {
			System.err.println("lista vuota");
			flag = false;
		}
		if (flag) {
			System.out.println("credenziali accetate");
		} else {
			System.err.println("riprova");
		}
		return flag;
	}

	//////////////////////////// Utente\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	@RequestMapping(value = "/insertUtente", method = RequestMethod.POST)
	public void register(Utente u, Account a, int idPermesso) throws InterruptedException {//insert and update
		System.out.println("method of register");
		System.out.println("utente : " + u.toString() + a.toString());
		Permesso p = rp.findById(idPermesso);
		System.out.println("permesso p : " + p.toString());
		a.setRuolo(p);
		u.setAccount(a);
		System.out.println("utente : " + u.toString());
		ru.save(u);
//		Thread.sleep(375);
//		return ru.findAll(); java.lang.IllegalStateException: Cannot call sendError() after the response has been committed
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public List<Utente> delete(@RequestParam int idUtente) {
		boolean flag = false;
		System.out.println("method of delete");
//		Utente u = ru.findById(idUtente);
//		System.out.println("utente : " + u.toString());
//		u.setAccount(a);
//		a.setRuolo(p);
		ru.deleteById(idUtente);
		return ru.findAll();
	}

	@RequestMapping(value = "tabellaUteni", method = RequestMethod.GET)
	public String tabellaUtenti(HttpSession session) {
		session.setAttribute("TabUtenti", ru.findAll());
		return "tabellaUtenti";
	}

	@RequestMapping(value = "tabellaUtente", method = RequestMethod.GET)
	public String tabellaUtente(@RequestParam int idUtente, HttpSession session) {
		session.setAttribute("TabUtente", ru.findById(idUtente));
		return "tabellaUtente";
	}

	////////////////////// permesso\\\\\\\\\\\\\\\\\\\\ sembra funzionare tutti per il permesso
	@RequestMapping(value = "inserRuolo", method = RequestMethod.POST)
	public List<Permesso> insertRuolo(Permesso p) {// con la versione 3 di spring non riesco a fargli predere l'auto build							// dell'obj from html
		rp.save(p);// work
		return rp.findAll();
	}

//	@RequestMapping(value = "updateRuolo", method = RequestMethod.POST)
//	public List<Permesso> modRuolo(Permesso p) {
//		rp.save(p);// work
//		return rp.findAll();
//	}

	@RequestMapping(value = "deleteRuolo", method = RequestMethod.DELETE)
	public List<Permesso> deleteRuolo(Permesso p) {
		rp.delete(p);// work
		return rp.findAll();
	}

	@RequestMapping(value = "readRuoli", method = RequestMethod.GET)
	public List<Permesso> readRuolo(@RequestParam boolean all, String ruolo) {
		if (all) {
			return rp.findAll();// work
		} else {
			Permesso p = new Permesso();
			p.setRuolo(ruolo);
			List<Permesso> listOneRuolo = new ArrayList<Permesso>();
			listOneRuolo.add(p);
			return listOneRuolo; //work
		}
	}

}
