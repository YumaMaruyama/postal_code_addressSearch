package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domail.service.ZipcodeService;
import com.example.demo.model.ZipcodeDTO;

@Controller
public class ZipCodeControllerController {

	@Autowired
	ZipcodeService zipcodeService;

	//郵便番号で住所検索画面
	@GetMapping("/zipcode")
	public String getZipcodeForm(HttpSession session, Model model) {

		return "zipcode";
	}



	//検索結果画面
	@PostMapping(value = "/confirm",params = "search")
	public String postZipcodeConfirm(HttpSession session, Model model, @RequestParam("zipcode") String zipcode) {
		
		
		//null or 空文字の場合はエラーメッセージを表示
		if (zipcode == null || zipcode.isEmpty()) {
			System.out.println("エラーチェック到達");
			model.addAttribute("result", "正しい郵便番号を入力してください");
			return getZipcodeForm(session, model);
		}

		//郵便番号検索APIサービスの呼び出し
		ZipcodeDTO zipcodedto = zipcodeService.service(zipcode);
		System.out.println("URL   " + zipcodedto);
		model.addAttribute("zipcodeList", zipcodedto.getResults());

		return "zipcode-confirm";

	}

	//住所検索画面に遷移する
	@PostMapping(value = "/confirm", params = "home")
	public String postConfirmHome(HttpSession session,Model model) {

		return "zipcode";
	}

}
