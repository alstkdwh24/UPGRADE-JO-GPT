package com.geomin.project.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.geomin.project.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.geomin.project.cart.service.CartService;
import com.geomin.project.command.CartVO;
import com.geomin.project.command.GameContentVO;
import com.geomin.project.command.PageVO;
import com.geomin.project.command.PageVOquestion;
import com.geomin.project.command.PurchaseVO;
import com.geomin.project.command.QnaVO;
import com.geomin.project.command.UserVO;
import com.geomin.project.gameContentService.GameContentService;
import com.geomin.project.service.command.CommandService;
import com.geomin.project.user.service.UserService;
import com.geomin.project.util.Criteria;
import com.geomin.project.util.CriteriaQuestion;
import com.geomin.project.util.JCriteria;
import com.geomin.project.util.JPageVO;

@Controller
@RequestMapping("/command")
public class CommandController {

	@Autowired

	private CartService cartService;

	@Autowired
	private GameContentService gameContentService;

	@Autowired
	private CommandService commandService;

	@Autowired
	private UserService userService;

	private HttpSession httpSession;

	// 회원 정보 수정
	@GetMapping("/modify")
	public String modify() {
		return "command/modify";
	}

	// 게임 컨텐츠 목록 - 일반 사용자 / 선생님
	@GetMapping("/gameList")
	public String gameList(HttpServletRequest request, Model model,
						   JCriteria JCri, HttpSession session /* @RequestParam("user_no") int user_no, @RequestParam("game_no") int game_no */) {
        ArrayList<GameContentVO> list = commandService.getList(JCri);
//		cartService.addtoCart(user_no, game_no);

		UserVO vo = (UserVO) session.getAttribute("vo");
		int user_no = vo.user_no;
		model.addAttribute("user_no",user_no);

        String roles = (String) session.getAttribute("user");
        model.addAttribute("userSession", roles);


        List<GameContentVO> pagesubList = safeList(list, 0, 10);

        List<GameContentVO> pagesubListTwo = safeList(list, 5, 10);

        int total = commandService.getTotal(JCri);
        JPageVO JPageVO = new JPageVO(JCri, total);

        model.addAttribute("JPageVO", JPageVO);
        model.addAttribute("pagesubList", pagesubList);
        model.addAttribute("pagesubListTwo", pagesubListTwo);


        return "command/gameList";
    }

	public static List<GameContentVO> safeList(List<GameContentVO> list, int fromIndex, int toIndex) {
		int actualToIndex = Math.min(list.size(), toIndex);
		if (fromIndex >= list.size()) {
			return new ArrayList<>();

		}
		return list.subList(fromIndex, actualToIndex);
	}

	// 게임 컨텐츠 목록 - 일반 사용자 / 선생님
	@GetMapping("/lookup")
	public String lookup(HttpServletRequest request, Model model, Criteria criteria) {

		/*
		 * HttpSession session = request.getSession(); UserVO vo = (UserVO)
		 * session.getAttribute("vo"); int user_no = Integer.parseInt(vo.user_no);
		 * ArrayList<CartVO> cartList = cartService.getListCart(user_no);
		 * model.addAttribute("vo", vo); model.addAttribute("cartList", cartList);
		 */

		ArrayList<GameContentVO> list = gameContentService.getList(criteria);
		int total = gameContentService.getTotal();
		PageVO vo = new PageVO(criteria, total);
		model.addAttribute("gameContent", list);
		model.addAttribute("pageVO", vo);

		return "command/lookup";
	}

	// 나의 구독 조회
	@GetMapping("/myproduct")
	public String myproduct(HttpServletRequest request, Model model) {

		HttpSession session = request.getSession();
		UserVO vo = (UserVO) session.getAttribute("vo");
		int user_no = vo.user_no;

		ArrayList<PurchaseVO> purList = cartService.purchaseHistory(user_no);
		model.addAttribute("purList", purList);

		// 이미지 포함
		ArrayList<PurchaseVO> purListWithImg = cartService.purchaseHistoryWithImg(user_no);
		model.addAttribute("listWithImg", purListWithImg);

		return "command/myproduct";
	}

	@GetMapping("/cart")
	public String cart(HttpServletRequest request, Model model) {

		HttpSession session = request.getSession();
		UserVO vo = (UserVO) session.getAttribute("vo");
		model.addAttribute("vo", vo);

		int user_no = vo.user_no;
		ArrayList<CartVO> cartList = cartService.getListCart(user_no);

		if (cartList != null) {
			int total_price = 0;

			for (CartVO c : cartList) {
				total_price += c.getGame_price();
			}

			System.out.println(total_price);

			model.addAttribute("cartList", cartList);
			model.addAttribute("total_price", total_price);
			return "command/cart";
		}

		return "command/cart";
	}

	@GetMapping("/payment")
	public String payment(HttpServletRequest request, Model model) {

		HttpSession session = request.getSession();
		UserVO vo = (UserVO) session.getAttribute("vo");
		model.addAttribute("vo", vo);

		int user_no = vo.user_no;
		ArrayList<CartVO> cartList = cartService.getListCart(user_no);

		if (cartList != null) {
			int total_price = 0;

			for (CartVO c : cartList) {
				total_price += c.getGame_price();
			}

			System.out.println(total_price);

			model.addAttribute("cartList", cartList);
			model.addAttribute("total_price", total_price);
			return "command/payment";
		}

		return "command/payment";
	}

	@GetMapping("/question")
	public String question(Model model, CriteriaQuestion criteria, HttpServletRequest request) {

		HttpSession session = request.getSession();
		UserVO userVO = (UserVO) session.getAttribute("vo");
		int user_no = userVO.user_no;
		model.addAttribute("userVO", userVO);

		ArrayList<QnaVO> list = userService.getQnaList(user_no, criteria);
		int total = userService.getQnaTotal(user_no);
		PageVOquestion vo = new PageVOquestion(criteria, total);

		model.addAttribute("Qnalist", list);
		model.addAttribute("pageVO", vo);

		return "command/question";
	}

	@GetMapping("/qnaRegist")
	public String qnaRegist(QnaVO vo, RedirectAttributes ra) {

		int result = userService.qnaRegist(vo);
		if (result == 1) {
			ra.addFlashAttribute("msg", "정상적으로 처리되었습니다."); // 리다이렉트시 1회성
		} else { // 실패
			ra.addFlashAttribute("msg", "등록에 실패했습니다. 관리자에게 문의하세요. 8282-8282");
		}

		return "redirect:/command/question";
	}

	@GetMapping("/qnaDelete")
	public String qnaDelete(@RequestParam("qna_no") int qna_no) {
		System.out.println("삭제 기능 :: " + qna_no);
		userService.qnaDelete(qna_no);
		return "redirect:/command/question";
	}

}
