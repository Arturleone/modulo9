//package com.example.modulo9
//
//import android.os.Bundle
//import android.util.Log
//import android.widget.SearchView
//import android.widget.Toast
//import androidx.activity.viewModels
//import androidx.appcompat.app.AppCompatActivity
//import androidx.recyclerview.widget.ItemTouchHelper
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.google.android.material.bottomnavigation.BottomNavigationView
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.withContext
//
//class ComplaintsListActivity : AppCompatActivity() {
//
//    private lateinit var recyclerView: RecyclerView
//    private lateinit var searchView: SearchView
//    private lateinit var bottomNav: BottomNavigationView
//    private val viewModel: ComplaintsViewModel by viewModels() // ViewModel para gerenciar dados
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_complaints_list)
//
////        recyclerView = findViewById(R.id.recyclerView)
////        searchView = findViewById(R.id.searchView)
//        bottomNav = findViewById(R.id.bottomNav)
//
//        setupRecyclerView()
//        setupBottomNav()
//        setupSearchView()
//        loadComplaints()
//    }
//
//    private fun setupRecyclerView() {
//        recyclerView.layoutManager = LinearLayoutManager(this)
//        val adapter = ComplaintsAdapter(viewModel.getComplaints()) { complaint, action ->
//            when (action) {
//                "edit" -> editComplaint(complaint)
//                "delete" -> deleteComplaint(complaint)
//            }
//        }
//        recyclerView.adapter = adapter
//
//        // Drag and Drop
//        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.UP or ItemTouchHelper.DOWN) {
//            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
//                val fromPosition = viewHolder.adapterPosition
//                val toPosition = target.adapterPosition
//                adapter.notifyItemMoved(fromPosition, toPosition)
//                // Persistir a nova ordem na API se necessário
//                return true
//            }
//
//            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//                // Não usar swipe para exclusão
//            }
//        })
//        itemTouchHelper.attachToRecyclerView(recyclerView)
//    }
//
//    private fun setupSearchView() {
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                // Filtrar lista de reclamações
//                (recyclerView.adapter as ComplaintsAdapter).filter(newText)
//                return true
//            }
//        })
//    }
//
//    private fun loadComplaints() {
//        CoroutineScope(Dispatchers.IO).launch {
//            try {
//                val response = ApiClient.api.getComplaints() // Método para buscar reclamações
//                withContext(Dispatchers.Main) {
//                    if (response.isSuccessful) {
//                        viewModel.setComplaints(response.body() ?: emptyList())
//                        (recyclerView.adapter as ComplaintsAdapter).updateData(viewModel.getComplaints())
//                    } else {
//                        Log.e("Erro", "Falha ao carregar reclamações: ${response.code()}")
//                    }
//                }
//            } catch (e: Exception) {
//                withContext(Dispatchers.Main) {
//                    Toast.makeText(this@ComplaintsListActivity, "Falha na conexão!", Toast.LENGTH_SHORT).show()
//                }
//            }
//        }
//    }
//
//    private fun editComplaint(complaint: Complaint) {
//        // Implementar lógica para editar reclamação
//    }
//
//    private fun deleteComplaint(complaint: Complaint) {
//        // Implementar lógica para excluir reclamação
//    }
//
//    private fun setupBottomNav() {
//        bottomNav.setOnItemSelectedListener {
//            when (it.itemId) {
//                R.id.action_register -> startActivity(Intent(this, RegisterComplaintActivity::class.java))
//                // Adicione outros itens de menu conforme necessário
//            }
//            true
//        }
//    }
//}
