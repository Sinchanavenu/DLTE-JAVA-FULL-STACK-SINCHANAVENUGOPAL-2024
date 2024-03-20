//package basic.service;
//
//import static org.mockito.Mockito.*;
//
//import basic.service.Entity.UserDetails;
//import basic.service.middleware.UserDetailsDatabaseRepository;
//import basic.service.remotes.StorageTarget;
//import basic.service.services.UserDetailsServices;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import java.util.Date;
//
///**
// * Unit test for simple App.
// */
//
//@RunWith(MockitoJUnitRunner.class)
//public class AppTest{
//        @Mock
//        private StorageTarget mockStorageTarget;
//        @Mock
//        private UserDetailsDatabaseRepository mockDatabaseRepository;
//        @Mock
//        private UserDetailsServices services;
//
//        @Before
//        public void prepareStore(){
//            when(mockStorageTarget.getUserDetailsRepository()).thenReturn(mockDatabaseRepository);
//            services = new UserDetailsServices(mockStorageTarget);
//        }
//        @Test
//        public void testCallPasswordValidate_InvalidPassword () {
//            // Given
//            String username = "sinchana";
//            String password = "Sinchana@1";
//
//            UserDetails userDetails = new UserDetails("sinchana", "Sinchana@1", new Date("12/2/2002"), "mangalore", "sinch12@gmail.com", 9876541230L);
//            // When
//            when(mockDatabaseRepository.verifyPassword(username, password)).thenReturn(false);
//            UserDetails result = services.callVerifyPassword(userDetails.getuserName(), userDetails.getpassword());
//            // Then
//            assertFalse(result);
//        }
//
//    private void assertFalse(UserDetails result) {
//    }
//
//    @Test
//        public void testUpdate () {
//            UserDetails userDetails1 = new UserDetails("sinchana", "Sinchana@1", new Date("12/2/2028"), "mangalore", "sinch12@gmail.com", 9876541230L);
//            UserDetails userDetails2 = new UserDetails("annapoornapai", "anna", new Date("06/07/2002"), "karkala", "annapoorna@gmail.com", 6363276256L);
//            doNothing().when(mockDatabaseRepository).update(userDetails1);
//            services.callUpdate(userDetails2);
//            services.calladdusers();
//            verify(mockDatabaseRepository).update(userDetails1);
//        }
//    }