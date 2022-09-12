package guru.springframework.spring5recipeapp.domain;

import lombok.Data;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.aspectj.weaver.ast.Not;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Recipe
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;

    @Lob
    private String directions;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "recipe")
    private Set<Ingredient> ingredients = new HashSet<>();

    @Lob
    private byte[] image;

    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;

    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;

    @ManyToMany
    @JoinTable(name = "recipe_category",
               joinColumns = @JoinColumn(name = "recipe_id"), //this side
               inverseJoinColumns = @JoinColumn(name = "category_id")) //other side of rel

    private Set<Category> categories = new HashSet<>();

    public void setNotes(Notes notes)
    {
        if (notes!=null)
        {
            this.notes = notes;
            notes.setRecipe(this);
        }
    }

    public Recipe addIngredient(Ingredient ingredient)
    {
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return this;
    }
}
