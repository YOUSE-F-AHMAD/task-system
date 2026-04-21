package com.example.task_system.noteBook.service;

import com.example.task_system.exception.BusinessException;
import com.example.task_system.noteBook.NoteBook;
import com.example.task_system.noteBook.repository.NoteBookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NoteBookServiceTest {

    @InjectMocks
    private NoteBookService noteBookService;

    @Mock
    private NoteBookRepository repository;

    private NoteBook noteBook;

    @BeforeEach
    void setUp(){
      this.noteBook = NoteBook.builder()
              .name("NoteL662Y")
              .build();

    }


@Nested
class CreateNoteBook{

    @Test
    public void createNoteBook(){
        //Given
        final String name = "NoteL662Y";
        when(repository.save(any(NoteBook.class)))
                .thenReturn(noteBook);

        //When
        final NoteBook noteBook1 = noteBookService.createNewNoteBook(name);
        //Then
        Assertions.assertEquals(noteBook1,noteBook);
        Mockito.verify(repository,Mockito.times(1))
                .save(any(NoteBook.class));
        Mockito.verify(repository)
                .save(ArgumentMatchers
                        .argThat(noteBook2 -> noteBook2.getName().equals(noteBook.getName())));

    }

    @Test
    public void noteBookWithNullName(){
        //Given
        final String name = null;
        //When Then
        final BusinessException exception = Assertions.assertThrows(
                BusinessException.class,
                () -> noteBookService.createNewNoteBook(name)
        );
        Assertions.assertEquals("please insert the noteBook's name",exception.getMessage());
        Mockito.verifyNoInteractions(repository);
    }
}

}