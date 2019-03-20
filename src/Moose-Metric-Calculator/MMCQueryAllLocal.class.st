Class {
	#name : #MMCQueryAllLocal,
	#superclass : #MMCAbstractQuery,
	#category : #'Moose-Metric-Calculator-Query'
}

{ #category : #accessing }
MMCQueryAllLocal class >> displayName [
	^ 'queryAllLocal:'
]

{ #category : #accessing }
MMCQueryAllLocal >> searchAssociations [
	^ self searchAssociationWithoutAssociationClass
]
