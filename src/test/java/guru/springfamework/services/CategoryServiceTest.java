package guru.springfamework.services;

import guru.springfamework.api.v1.mappers.CategoryMapper;
import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.domain.Category;
import guru.springfamework.repositories.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

public class CategoryServiceTest {

    private static final Long ID = 2L;
    private static final String NAME = "Jimmy";
    private CategoryDTO expectedCategeoryDto = new CategoryDTO();
    private Category cat = new Category();

    private CategoryService categoryService;

    @Mock
    CategoryRepository categoryRepository;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        categoryService = new CategoryServiceImpl(CategoryMapper.INSTANCE, categoryRepository);
        expectedCategeoryDto.setName(NAME);
        expectedCategeoryDto.setId(ID);
        cat.setName(NAME);
        cat.setId(ID);
    }

    @Test
    public void getAllCategories() {

        List<Category> cats = Arrays.asList(new Category(), new Category(), new Category());

        when(categoryRepository.findAll()).thenReturn(cats);

        List<CategoryDTO> fromService = categoryService.getAllCategories();

        assertEquals(3, fromService.size());

    }

    @Test
    public void getCategoryByName() {
        when(categoryRepository.findByName(anyString())).thenReturn(cat);

        CategoryDTO fromService = categoryService.getCategoryByName(NAME);

        assertEquals(expectedCategeoryDto, fromService);
    }

}