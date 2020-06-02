package com.sorry.buskingbusking.repository;

import com.sorry.buskingbusking.Repository.MemberRepository;
import com.sorry.buskingbusking.domain.Address;
import com.sorry.buskingbusking.domain.CommonFile;
import com.sorry.buskingbusking.domain.Member;
import com.sorry.buskingbusking.setting.FileSetting;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MemberRepositoryTest {

    Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private MemberRepository memberRepository;

    MultipartFile multipartFile;
    MultipartFile updateFile;

    Address address;
    Address updateAddress;


    private MultipartFile makeMockMultiPartFile(String fileNm) throws IOException {
        String fileDir = FileSetting.MOCK_FILE_PATH.getValue();
        String fileName = fileNm;
        String fileFullPath = fileDir + "/" + fileName;
        File file = new File(fileFullPath);
        return new MockMultipartFile(fileName, new FileInputStream(file));
    }

    @Before
    public void 파일준비() throws IOException {
        multipartFile = makeMockMultiPartFile("profile_file.PNG");
        updateFile = makeMockMultiPartFile("update_file.PNG");
    }

    @Before
    public void 주소준비(){
        address = Address.builder()
                        .zipcode("04967")
                        .address1("서울특별시 광진구")
                        .address2("광장로7길 24")
                        .addressExt("1층")
                        .build();

        updateAddress = Address.builder()
                .zipcode("11935")
                .address1("경기도 구리시")
                .address2("교문동 746-3")
                .addressExt("1층")
                .build();
    }


    @Test
    public void 회원가입_저장() throws IOException{
        CommonFile profile = new CommonFile();
        profile.saveFileList(multipartFile);
        testEntityManager.persist(profile);

        Member member = Member.builder()
                .email("wivipp39@naver.com")
                .profile(profile)
                .password("1234")
                .nickName("sorryisme")
                .mobile("010-7146-7182")
                .msgYn("Y")
                .msgId("wivipp39")
                .delYn("N")
                .address(address)
                .regDt(LocalDateTime.now())
                .updDt(LocalDateTime.now()).build();

        testEntityManager.persist(member);
        Member findMember = memberRepository.getOne(member.getId());
        assertThat(findMember).isEqualTo(member);
        assertThat(findMember.getEmail()).isEqualTo("wivipp39@naver.com");
        assertThat(findMember.getProfile()).isEqualTo(profile);
        assertThat(findMember.getProfile().getFileSize()).isEqualTo(multipartFile.getSize());
        assertThat(findMember.getProfile().getFileRealName()).isEqualTo(multipartFile.getOriginalFilename());
        assertThat(findMember.getAddress()).isEqualTo(address);
        assertThat(findMember.getAddress().getAddress1()).isEqualTo(address.getAddress1());
    }

    @Test
    public void 회원저장_조회() throws IOException {
        CommonFile profile = new CommonFile();
        profile.saveFileList(multipartFile);
        testEntityManager.persist(profile);

        Member member1 =  Member.builder()
                .email("test@naver.com")
                .profile(profile)
                .password("1234")
                .nickName("nick1")
                .mobile("010-7146-7182")
                .msgYn("Y")
                .msgId("wivipp39")
                .delYn("N")
                .address(address)
                .regDt(LocalDateTime.now())
                .updDt(LocalDateTime.now()).build();

        testEntityManager.persist(member1);
        Member member2 = Member.builder()
                .email("test2@naver.com")
                .profile(profile)
                .password("1234")
                .nickName("test2")
                .mobile("010-7146-7182")
                .msgYn("Y")
                .msgId("wivipp39")
                .delYn("N")
                .address(address)
                .regDt(LocalDateTime.now())
                .updDt(LocalDateTime.now()).build();

        testEntityManager.persist(member2);
        Member member3 = Member.builder()
                .email("test3@naver.com")
                .profile(profile)
                .password("1234")
                .nickName("test2")
                .mobile("010-6647-7182")
                .msgYn("Y")
                .msgId("apple")
                .delYn("N")
                .address(address)
                .regDt(LocalDateTime.now())
                .updDt(LocalDateTime.now()).build();
        testEntityManager.persist(member3);

        List<Member> memberList = memberRepository.findAll();
        assertThat(memberList).hasSize(3);
        assertThat(memberList).contains(member1,member2,member3);
    }

    //TODO 1)DTO에서 MultipartFile CommonFile로 전환하는 것을 고려해볼 것
    @Test
    public void 회원정보_업데이트() throws IOException{
        CommonFile profile = new CommonFile();
        profile.saveFileList(multipartFile);
        testEntityManager.persist(profile);
        //given
        Member savedMember = Member.builder()
                .email("wivipp39@naver.com")
                .profile(profile)
                .password("1234")
                .nickName("sorryisme")
                .mobile("010-7146-7182")
                .msgYn("Y")
                .msgId("wivipp39")
                .delYn("N")
                .address(address)
                .regDt(LocalDateTime.now())
                .updDt(LocalDateTime.now()).build();

        testEntityManager.persist(savedMember);

        Member updateMember = Member.builder()
                .id(savedMember.getId())
                .email("updated@0naver.com")
                .password("9999")
                .nickName("jisungkim")
                .msgId("N")
                .msgId(null)
                .address(updateAddress)
                .delYn("N")
                .updDt(LocalDateTime.now())
                .build();


        String filePath = savedMember.getProfile().getFullPath();
        savedMember.getProfile().updateFile(filePath, updateFile);
        savedMember.updateMember(updateMember);
        testEntityManager.flush();

        Optional<Member> optionalMember = memberRepository.findById(savedMember.getId());
        Member updatedMember = optionalMember.get();

        assertThat(updatedMember).isNotNull();
        assertThat(updatedMember.getEmail()).isEqualTo("updated@0naver.com");
        assertThat(updatedMember.getPassword()).isEqualTo("9999");
        assertThat(updatedMember.getNickName()).isEqualTo("jisungkim");
        assertThat(updatedMember.getAddress()).isEqualTo(updateAddress);
        assertThat(updatedMember.getProfile().getFileRealName()).isEqualTo(updateFile.getOriginalFilename());
    }


}

