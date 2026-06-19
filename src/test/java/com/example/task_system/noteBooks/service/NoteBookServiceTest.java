package com.example.task_system.noteBooks.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class NoteBookServiceTest {
//
//    @InjectMocks
//    private NoteBookService noteBookService;
//
//    @Mock
//    private NoteBookRepository repository;
//
//    @Mock
//    private UserRepository userRepository;
//
//    @Mock
//    private UserNoteBookRepository userNoteBookRepository;
//
//    private Users user;
//
//    private NoteBook noteBook;
//
//    private CreateNoteBookRequest createNoteBookRequest;
//
//    private UserNoteBook userNoteBook;
//
//    @BeforeEach
//    void setUp(){
//        this.user = Users.builder()
//                .id(1L)
//                .firstName("yousef")
//                .lastName("ahmad")
//                .email("yozarsef20003000@gmail.com")
//                .password("lithsamer")
//                .build();
//
//        this.noteBook = NoteBook.builder()
//              .name("NoteL662Y")
//              .build();
//
//        this.userNoteBook = UserNoteBook.builder()
//                .id(1L)
//                .noteBook(noteBook)
//                .user(user)
//                .build();
//
//        this.createNoteBookRequest = CreateNoteBookRequest.builder()
//              .name("NoteL662Y")
//              .userId(1L)
//              .build();
//
//    }
//
//
//@Nested
//class CreateNoteBook{
//
//    @Test
//    public void createNoteBook(){
//        //Given
//        when(userRepository.findById(createNoteBookRequest.getUserId()))
//                .thenReturn(Optional.of(user));
//
//        when(repository.save(any(NoteBook.class)))
//                .thenReturn(noteBook);
//
//        when(userNoteBookRepository.save(any(UserNoteBook.class)))
//                .thenReturn(userNoteBook);
//
//        //When
//        final NoteBook noteBook1 = noteBookService.createNewNoteBook(createNoteBookRequest);
//        //Then
//        Assertions.assertEquals(noteBook1,noteBook);
//
//        Mockito.verify(userRepository,Mockito.times(1))
//                .findById(createNoteBookRequest.getUserId());
//
//        Mockito.verify(repository,Mockito.times(1))
//                .save(any(NoteBook.class));
//
//        Mockito.verify(userNoteBookRepository,Mockito.times(1))
//                        .save(any(UserNoteBook.class));
//
//        Mockito.verify(repository)
//                .save(ArgumentMatchers
//                        .argThat(noteBook2 -> noteBook2.getName().equals(noteBook.getName())));
//    }
//
//    @Test
//    public void noteBookWithNullName(){
//        //Given
//        createNoteBookRequest.setName(null);
//        //When Then
//        final BusinessException exception = Assertions.assertThrows(
//                BusinessException.class,
//                () -> noteBookService.createNewNoteBook(createNoteBookRequest)
//        );
//        Assertions.assertEquals("please insert the noteBook's name",exception.getMessage());
//        Mockito.verifyNoInteractions(repository);
//    }
//}
}