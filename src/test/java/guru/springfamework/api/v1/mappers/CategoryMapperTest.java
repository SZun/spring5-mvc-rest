package guru.springfamework.api.v1.mappers;

import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryMapperTest {

    private static final String NAME = "Joe";
    private static final long ID = 1L;
    private final CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    private CategoryDTO expectedCategeoryDto = new CategoryDTO();
    private Category cat = new Category();

    @Before
    public void setUp() throws Exception {
        expectedCategeoryDto.setName(NAME);
        expectedCategeoryDto.setId(ID);
        cat.setName(NAME);
        cat.setId(ID);
    }

    @Test
    public void categoryToCategoryDTO() {
        CategoryDTO fromMapper = categoryMapper.categoryToCategoryDTO(cat);
        assertEquals(expectedCategeoryDto, fromMapper);
    }
}