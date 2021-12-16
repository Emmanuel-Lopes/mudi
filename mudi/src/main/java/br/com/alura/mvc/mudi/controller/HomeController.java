package br.com.alura.mvc.mudi.controller;

//import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;
import br.com.alura.mvc.mudi.repository.PedidoRepository;

@Controller
@RequestMapping("/home")
public class HomeController{

    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping
    public String home(Model model) {
        /*Pedido pedido = new Pedido();
        pedido.setNomeProduto("Poco X3 PRO");
        pedido.setUrlImagem("https://m.media-amazon.com/images/I/413ImpBj5ES._AC_SR38,50_.jpg");
        pedido.setUrlProduto("https://www.amazon.com.br/Smartphone-Poco-PRO-256gb-8gb/dp/B0919PLV7S?ref_=Oct_d_otopr_d_16243803011&pd_rd_w=XcusZ&pf_rd_p=716b5142-7b40-4cb8-94a9-c2d932cb1153&pf_rd_r=MH2RZ5KPFPNFKX1MAKJ8&pd_rd_r=d9db7891-7a65-47a8-a2cb-4f982523c27d&pd_rd_wg=Z7HcD&pd_rd_i=B0919PLV7S");
        pedido.setDescricao("uma descrição qualquer");
       
        List<Pedido> pedidos = Arrays.asList(pedido);*/
        
        List<Pedido> pedidos = pedidoRepository.findAll();
        model.addAttribute("pedidos", pedidos);

        return "home";
    }
    
    @GetMapping("/{status}")
    public String porStatus(@PathVariable("status") String status, Model model) {
        List<Pedido> pedidos = pedidoRepository.findAByStatus(StatusPedido.valueOf(status.toUpperCase()));
        model.addAttribute("pedidos", pedidos);
        model.addAttribute("status", status);

        return "home";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String onError() {
        return "redirect:/home";
    }
}
