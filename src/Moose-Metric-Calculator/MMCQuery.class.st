Class {
	#name : #MMCQuery,
	#superclass : #Object,
	#instVars : [
		'association',
		'method',
		'direction',
		'famixClass'
	],
	#category : #'Moose-Metric-Calculator-Query'
}

{ #category : #accessing }
MMCQuery >> association [
	^ association
]

{ #category : #accessing }
MMCQuery >> association: anAssociation [
	association := anAssociation
]

{ #category : #accessing }
MMCQuery >> direction [
	^ direction
]

{ #category : #accessing }
MMCQuery >> direction: aSymbol [
	direction := aSymbol
]

{ #category : #accessing }
MMCQuery >> famixClass [
	^ famixClass
]

{ #category : #accessing }
MMCQuery >> famixClass: aFAMIXClass [
	famixClass := aFAMIXClass
]

{ #category : #accessing }
MMCQuery >> method [
	^ method,':with:'
]

{ #category : #accessing }
MMCQuery >> method: aString [ 
	method:= aString
	
]

{ #category : #accessing }
MMCQuery >> searchAssociations [
	^ self famixClass
		perform: self method asSymbol
		with: self direction
		with: self association
]
