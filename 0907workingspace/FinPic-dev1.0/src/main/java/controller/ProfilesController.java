package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import profilesDTO.Profiles;
import repositories.ProfilesRepository;
import repositories.UsersRepository;
import usersDTO.Users;

@RestController
public class ProfilesController {
	private final ProfilesRepository profilesRepository;
	private final UsersRepository usersRepository;

	ProfilesController(ProfilesRepository profilesRepository, UsersRepository usersRepository) {
		this.profilesRepository = profilesRepository;
		this.usersRepository = usersRepository;
	}

	// 프로필 정보 전달
	@GetMapping("/users/{userEmail}")
	public Profiles getProfile(@PathVariable String userEmail) {
		Users user = usersRepository.findByUserEmail(userEmail);
		return profilesRepository.findByUserId(user);
	}

	// 프로필 이미지 가져오기
	@GetMapping("/users/{userEmail}/profileImg")
	public String getProfileImg(@PathVariable String userEmail) throws IOException {
		Users user = usersRepository.findByUserEmail(userEmail);
		if (user == null)
			return null;
		String profileImgBase64 = null;
		String fileName = userEmail;
		File file = new File("C:/OpenPose/FinPic/img/users/");
		File files [] = file.listFiles();
		for(File f : files) {
			String fileExt = f.getName().substring(f.getName().lastIndexOf(".") + 1);
			if (f.getName().equals(fileName + "." + fileExt)) {
				FileInputStream in = new FileInputStream(f);
				byte bytes[] = new byte[(int)f.length()];
				in.read(bytes);
				String encodedfile = new String(Base64.encodeBase64(bytes), "UTF-8");
				profileImgBase64 = "data:image/" + fileExt + ";base64," + encodedfile;
				in.close();
				System.out.println(profileImgBase64);
				return profileImgBase64;
			}
		}
		System.out.println(profileImgBase64);
		return null;
	}

	// 프로필 사진 생성 및 업데이트
	@PutMapping("/users/{userEmail}/profileImageUpdate")
	public String updateProfileImage(@RequestParam("img") MultipartFile img, @PathVariable String userEmail) {
		Users user = usersRepository.findByUserEmail(userEmail);
		// 확장자 확인 작업
		// 프론트에서도 작업 가능
		String fileName = img.getOriginalFilename();
		String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1);
		String[] extList = { "gif", "jpg", "jpeg", "png" };
		boolean fileBool = false;
		for (String ext : extList) {
			if (fileExt.equals(ext)) {
				fileBool = true;
				break;
			}
		}
		if (!fileBool) {
			return "잘못된 확장자 파일 업로드.jsp";
		}

		if (user != null) {
			Profiles profile = profilesRepository.findByUserId(user);
			if (profile == null) {
				Profiles newProfilePic = Profiles.builder().userId(user).build();
				profilesRepository.save(newProfilePic);
				try {
					img.transferTo(new File("C:/OpenPose/FinPic/img/users/" + userEmail + "." + fileExt));
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			} else {
				try {
					img.transferTo(new File("C:/OpenPose/FinPic/img/users/" + userEmail + "." + fileExt));
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}
			return "redirect1:/somewhere.jsp";
		} else {
			return "redirect:/somewhere.jsp";
		}
	}

	// 프로필 정보 삽입 및 업데이트
	@PutMapping("/users/{uesrNumber}/profileUpdate")
	public String updateProfile(@RequestParam(required = false, value = "introduction") String introduction,
			@RequestParam(required = false, value = "snsLink1") String snsLink1,
			@RequestParam(required = false, value = "snsLink2") String snsLink2,
			@RequestParam(required = false, value = "snsLink3") String snsLink3, @PathVariable("uesrNumber") long userNumber) {
		Users user = usersRepository.findByUserNumber(userNumber);
		if (user != null) {
			Profiles profile = profilesRepository.findByUserId(user);
			if (profile == null) {
				Profiles newProfile = Profiles.builder().introduction(introduction).snsLink1(snsLink1)
						.snsLink2(snsLink2).snsLink3(snsLink3).userId(user).build();
				profilesRepository.save(newProfile);
			} else {
				profile.setIntroduction(introduction);
				profile.setSnsLink1(snsLink1);
				profile.setSnsLink2(snsLink2);
				profile.setSnsLink3(snsLink3);
				profilesRepository.save(profile);
			}
			return "redirect1:/somewhere.jsp";
		} else {
			return "redirect:/somewhere.jsp";
		}
	}

	// 프로필 이미지 삭제
	// 기본 프로필 이미지로 대체하는 방식
	@PutMapping("/users/{userEmail}/profileImageDel")
	public String deleteProfileImage(@PathVariable String userEmail) {
		Users user = usersRepository.findByUserEmail(userEmail);
		String result = "실패한 삭제.jsp";
		if (user != null) {
			Profiles profile = profilesRepository.findByUserId(user);
			if (profile != null) {
				String[] extention = { "gif", "jpg", "jpeg", "png" };
				String dirPath = "C:/OpenPose/FinPic/img/";
				for (String ext : extention) {
					File img = new File(dirPath + user.getUserEmail() + "_" + profile.getProfileNumber() + "." + ext);
					if (img.exists() == true) {
						img.delete();
						result = "성공적인 삭제.jsp";
					}
				}
			}
		}
		return result;
	}
}