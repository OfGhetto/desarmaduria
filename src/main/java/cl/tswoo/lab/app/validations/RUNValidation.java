package cl.tswoo.lab.app.validations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import cl.tswoo.lab.app.models.Cliente;
import cl.tswoo.lab.app.models.Vehiculo;
import cl.tswoo.lab.app.repositories.VehiculoRepository;
@Component
public class RUNValidation implements Validator {
	
	VehiculoRepository vehiculoRepository;

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Cliente.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		Cliente cliente=(Cliente) target;
		
		ValidationUtils.rejectIfEmpty(errors,"rutCliente", null, "No puede ser vacio");
		
		if(!validarRUN(cliente.getRutCliente()))
			errors.rejectValue("rutCliente", null,"RUN no valido");
		
	}

	
	private boolean validarRUN(String rut) {
        String rutValido=rut.toUpperCase();
		rutValido=rutValido.replace(".", "");
		rutValido=rutValido.replace("-", "");
		char verificador=rutValido.charAt(rutValido.length() - 1);
		rutValido=rutValido.substring(0, rutValido.length() - 1);
        StringBuilder stringBuilder = new StringBuilder(rutValido);
        String rutInvertido = stringBuilder.reverse().toString();
		int []arregloRut=new int [rutValido.length()];
		for(int i=0;i<rutValido.length();i++) {
			arregloRut[i]=Character.getNumericValue(rutInvertido.charAt(i));
    	}
	
		int rutSumado=0;
		
		int a = 2;
		for (int i = 0; i < arregloRut.length; i++) {
		  arregloRut[i] = arregloRut[i] * a;
		  rutSumado += arregloRut[i];
		  if (a == 7) {
		    a = 1;
		  }
		  a++;
		}
		int resto = rutSumado % 11;
		String Digito = String.valueOf(11 - resto);
		if (Digito.equals("11")) {
			  Digito = "0";
			}

		if (Digito.equals("10")) {
			  Digito = "K";
		}
		if((Digito.charAt(0))==verificador) {
			return true;
		}
		else {
			return false;
		}
	}


}
