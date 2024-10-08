package ar.edu.itba.paw.webapp.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;



// Usando los paquetes javax.validation.validation-api y org.hibernate.hibernate-validator que agregamos a los POM,
// podemos hacer uso de POJOs para validación de datos en un formulario. Esto nos permite simplificar enormemente
// el código de un formulario, cosa que aplicamos al HelloWorldController.register() a través de esta clase.
// Para que se use el formulario, en vez de pasar los parámetros del form como parámetros del método register(),
// cambiamos para que sea register(@Validate final UserForm userForm, final BindingResult errors).

// Para cada parámetro del form hacemos un campo privado en la clase. Cada uno de los campos del form se mappea por
// nombre con cada uno de los campos de esta clase.

// Podemos agregar annotations para sumar validaciones. No te olvides de crear getters y setters para todos estos!

// Para ver ejemplos de todos los constraints que se pueden poner, mirar javax.validation.constraints. Esto incluye
// @Min, @Max, @NotNull, @Size, @Pattern, y más. Además de los que se definen ahí (los de javax.validation son solo las
// definiciones, hibernate tiene las implementaciones) podemos ver los que suma hibernate suma otros validadores
// como @Email, @Length, @NotBlank, @NotEmpty, @URL, @SafeHtml, @Range, @CreditCardNumber...

public class UserForm {


    @Size(min = 8)
    @Pattern(regexp = "[a-zA-Z][a-zA-Z0-9]*")
    private String username;
    @Size(min = 8)
    private String password;
    @Size(min = 8)
    private String repeatPassword;

    public UserForm() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
