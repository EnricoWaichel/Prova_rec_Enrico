package service.carros;

import exception.CarrosException;
import model.entity.carros.Montadora;
import model.repository.MontadoraRepository;

public class MontadoraService {

    private MontadoraRepository montadoraRepository = new MontadoraRepository();

    public Montadora salvarMontadora(Montadora novaMontadora) throws CarrosException {
        validarCamposObrigatorios(novaMontadora);
        return montadoraRepository.salvar(novaMontadora);
    }
    
    public Montadora consultarMontadoraPorId(int id) {
        return montadoraRepository.consultarPorId(id);
    }

    private void validarCamposObrigatorios(Montadora montadora) throws CarrosException {
        String mensagemValidacao = "";
        
        if (montadora.getNome() == null || montadora.getNome().isEmpty()) {
            mensagemValidacao += " - Informe o nome da montadora\n";
        }
        if (montadora.getPaisFundacao() == null || montadora.getPaisFundacao().isEmpty()) {
            mensagemValidacao += " - Informe o país de fundação\n";
        }
        if (montadora.getNomePresidente() == null || montadora.getNomePresidente().isEmpty()) {
            mensagemValidacao += " - Informe o nome do presidente\n";
        }
        if (montadora.getDataFundacao() == null) {
            mensagemValidacao += " - Informe a data de fundação\n";
        }

        if (!mensagemValidacao.isEmpty()) {
            throw new CarrosException("Preencha o(s) seguinte(s) campo(s):\n" + mensagemValidacao);
        }
    }
}