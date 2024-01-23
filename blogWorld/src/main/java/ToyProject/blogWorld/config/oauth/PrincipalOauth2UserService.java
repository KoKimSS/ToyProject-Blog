package ToyProject.blogWorld.config.oauth;

import ToyProject.blogWorld.config.auth.PrincipalDetails;
import ToyProject.blogWorld.config.oauth.provider.*;
import ToyProject.blogWorld.entity.User.User;
import ToyProject.blogWorld.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;


    /**
     * 구글로 부터 받은 userRequest 데이터에 대한 후처리되는 함수
     * 함수 종료시 @AuthenticationPrincipal 어노테이션이 만들어진다
     */
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("userRequest : " + userRequest);

        System.out.println("userRequest.getClientRegistration() = " + userRequest.getClientRegistration());
        //registrationid 로 어떤 Oauth로 로그인 했는지 확인 ex) 'google'

        System.out.println("userRequest.getClientRegistration().getClientName() = " + userRequest.getClientRegistration().getClientName());
        System.out.println("userRequest.getAccessToken() = " + userRequest.getAccessToken());
        //구글 로그인 -> 로그인 창 -> 로그인 완료 -> code를 리턴(Oauth-Client 라이브러리) ->Access Token 요청
        //userRequest 정보 -> loadUser 함수 호출 -> 구글로부터 회원프로필 받음

        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println("loadUser(userRequest) = " + oAuth2User);
        System.out.println("oAuth2User.getAttributes() = " + oAuth2User.getAttributes());

        OAuth2UserInfo oAuth2UserInfo = null;
        //회원가입 강제 진행
        if (userRequest.getClientRegistration().getRegistrationId().equals("google")) {
            log.info("구글 로그인 요청");
            oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
        } else if (userRequest.getClientRegistration().getRegistrationId().equals("facebook")) {
            oAuth2UserInfo = new FacebookUserInfo(oAuth2User.getAttributes());
            log.info("페이스북 로그인 요청");
        } else if (userRequest.getClientRegistration().getRegistrationId().equals("naver")) {
            oAuth2UserInfo = new NaverUserInfo((Map) oAuth2User.getAttributes().get("response"));
            log.info("네이버 로그인 요청");
        }else if (userRequest.getClientRegistration().getRegistrationId().equals("kakao")){
            oAuth2UserInfo = new KakaoUserInfo(oAuth2User.getAttributes());
            log.info("카카오 로그인 요청");
        }
        else {
            System.out.println("우리는 구글,페이스북,네이버,카카오 만 지원합니다");
        }

        /**
         *  OAuth2UserInfo interface 를 통해 Facebook 과 Google 을 모두 담을 수 있는 새로운 부모 객체를 만듦
         */
        String provider = oAuth2UserInfo.getProvider();// google
        String providerId = oAuth2UserInfo.getProviderId();
        //구글 일 때만 sub ,facebook 일땐
        String email = oAuth2UserInfo.getEmail();
        String name = oAuth2UserInfo.getName();
        String OauthId = provider + "_" + providerId;// google_123122346969236788
        String password = bCryptPasswordEncoder.encode("겟인데어");


        Optional<User> userEntity = userRepository.findByLoginId(OauthId);
        User user;
        log.info("FindByOauthId Entity = {}",userEntity);
        if (!userEntity.isPresent()) {
            //user 로 역할 부여
            user = User.createOauthUser(OauthId,name, password, email, provider, providerId);
            userRepository.save(user);
        } else {
            user = userEntity.get();
            log.info("이미 회원가입 된 유저입니다");
        }


        return new PrincipalDetails(user, oAuth2User.getAttributes());
    }
}
